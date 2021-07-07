package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginResponse.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class LoginResponse {
    private String tokenType;
    private String accessToken;
    private Long id;
}
