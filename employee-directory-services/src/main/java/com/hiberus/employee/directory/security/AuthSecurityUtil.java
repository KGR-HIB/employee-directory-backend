package com.hiberus.employee.directory.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.hiberus.employee.directory.vo.User;

/**
 * AuthSecurityUtil.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 * @since 1.0.0
 */
public final class AuthSecurityUtil {

    /**
     * Obtiene el usuario logueado.
     * 
     * @author acachiguango on 30/06/2021
     * @return User
     */
    public static User getUserLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null == auth || !auth.isAuthenticated()) {
            return null;
        }
        User user = (User) auth.getPrincipal();
        return user;
    }

    /**
     * Constructor.
     */
    private AuthSecurityUtil() {
    }

}
