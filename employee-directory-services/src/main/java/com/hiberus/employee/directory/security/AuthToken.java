package com.hiberus.employee.directory.security;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import com.hiberus.employee.directory.vo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * AuthToken.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
@Component
@Lazy
public class AuthToken {

    /**
     * Obtiene token.
     * 
     * @author acachiguango on 30/06/2021
     * @param username
     * @return JWT
     */
    public String getAccessToken(User user) {
        if (null == user || null == user.getId()) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(user.toString())
            .claim(AuthConstants.AUTHORITIES,
                grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS256, AuthConstants.SIGNIN_KEY.getBytes()).compact();
    }
}
