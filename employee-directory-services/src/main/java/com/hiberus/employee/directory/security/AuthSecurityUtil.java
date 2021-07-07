package com.hiberus.employee.directory.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.employee.directory.vo.User;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthSecurityUtil.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
public final class AuthSecurityUtil {
    private static final ObjectMapper OBJECT_MAPPER =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Get logged in user.
     * 
     * @author acachiguango on 30/06/2021
     * @return User
     */
    public static User getUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null == auth || !auth.isAuthenticated()) {
            return null;
        }
        String principal = (String) auth.getPrincipal();
        if (StringUtils.isNotBlank(principal)) {
            try {
                return OBJECT_MAPPER.readValue(principal, User.class);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Constructor.
     */
    private AuthSecurityUtil() {
    }

}
