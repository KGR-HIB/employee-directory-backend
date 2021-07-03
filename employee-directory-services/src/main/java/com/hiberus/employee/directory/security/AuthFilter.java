package com.hiberus.employee.directory.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * JWTAuthorizationFilter.
 * 
 * @author acachiguango on 29/06/2021
 * @version 1.0
 */
public class AuthFilter extends OncePerRequestFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        try {
            if (checkJWTToken(request)) {
                Claims claims = validateToken(request);
                if (null != claims.get(AuthConstants.AUTHORITIES)) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    /**
     * Validate token.
     * 
     * @author acachiguango on 29/06/2021
     * @param request HttpServletRequest
     * @return Claims
     */
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AuthConstants.AUTHORIZATION).replace(AuthConstants.BEARER, "");
        return Jwts.parser().setSigningKey(AuthConstants.SIGNIN_KEY.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Authentication method in Spring.
     * 
     * @author acachiguango on 29/06/2021
     * @param claims Claims
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get(AuthConstants.AUTHORITIES);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    /**
     * Check JWT token.
     * 
     * @author acachiguango on 29/06/2021
     * @param request HttpServletRequest
     * @return boolean
     */
    private boolean checkJWTToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(AuthConstants.AUTHORIZATION);
        if (StringUtils.isBlank(authenticationHeader) || !authenticationHeader.startsWith(AuthConstants.BEARER)) {
            return false;
        }
        return true;
    }
}
