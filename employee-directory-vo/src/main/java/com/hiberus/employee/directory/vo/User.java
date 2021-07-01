package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Login.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
@ToString
@NoArgsConstructor
@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String password;
    private Boolean loginFirstTime;
    private String tokenType;
    private String accessToken;
}
