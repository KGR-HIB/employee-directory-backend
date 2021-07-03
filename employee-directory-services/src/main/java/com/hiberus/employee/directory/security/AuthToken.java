package com.hiberus.employee.directory.security;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.employee.directory.vo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthToken.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
@Slf4j
@Component
@Lazy
public class AuthToken {

    @Lazy
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Get token.
     * 
     * @author acachiguango on 30/06/2021
     * @param User user
     * @return JWT
     */
    public String getAccessToken(User user) {
        if (null == user || null == user.getId()) {
            return null;
        }
        String subject = null;
        try {
            subject = this.objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(subject)
            .claim(AuthConstants.AUTHORITIES,
                grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS256, AuthConstants.SIGNIN_KEY.getBytes()).compact();
    }
}
