package com.hiberus.employee.directory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.security.AuthConstants;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
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
    public ResponseEntity<Response<User>> login(@RequestBody User request) {
        User user = this.userService.login(request);
        if (null == user) {
            return new ResponseEntity<>(Response.<User>builder().message(AuthConstants.UNAUTHORIZED).build(),
                HttpStatus.UNAUTHORIZED);
        }
        user.setAccessToken(this.authToken.getAccessToken(user));
        user.setTokenType(AuthConstants.BEARER.trim());

//        User userLogin = AuthSecurityUtil.getUserLogin();
//        log.info(userLogin.getId().toString());
        return new ResponseEntity<>(Response.<User>builder().data(user).build(), HttpStatus.OK);
    }

}
