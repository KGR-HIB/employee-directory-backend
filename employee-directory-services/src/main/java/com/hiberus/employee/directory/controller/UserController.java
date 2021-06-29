package com.hiberus.employee.directory.controller;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Lazy
public class UserController {

    @Lazy
    @Autowired
    @Getter
    private IUserService userService;

    @PostMapping
    @Operation(summary = "Save user")
    public UserEntity save(@RequestBody UserEntity user) {
        this.userService.save(user);
        return user;
    }
}
