package com.hiberus.employee.directory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.security.AuthConstants;
import com.hiberus.employee.directory.security.AuthToken;
import com.hiberus.employee.directory.service.IUserService;
import com.hiberus.employee.directory.vo.User;
import com.hiberus.employee.directory.vo.common.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * AuthController
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@Lazy
@Tag(name = "Authorization", description = "Authorization API")
public class AuthController {

    @Lazy
    @Autowired
    @Getter
    private IUserService userService;

    @Lazy
    @Autowired
    @Getter
    private AuthToken authToken;

    /**
     * Login.
     * 
     * @author acachiguango on 30/06/2021
     * @param request User
     * @return Response User
     */
    @PostMapping("/login")
    public ResponseEntity<Response<User>> login(@Valid @RequestBody User request) {
        User user = this.userService.login(request);
        if (null == user) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Response.<User>builder().code(401).message(AuthConstants.UNAUTHORIZED).build());
        }
        user.setAccessToken(this.authToken.getAccessToken(user));
        user.setTokenType(AuthConstants.BEARER.trim());
        return ResponseEntity.ok()
            .body(Response.<User>builder().data(user).code(200).message(AuthConstants.SUCCESS).build());
    }

    /**
     * Logout.
     * 
     * @author acachiguango on 01/07/2021
     */
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().body(Response.<String>builder().code(200).message(AuthConstants.SUCCESS).build());
    }
}
