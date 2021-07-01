package com.hiberus.employee.directory.vo;

import com.hiberus.employee.directory.vo.validate.NotBlankConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login.
 * 
 * @author acachiguango on 30/06/2021
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    @NotBlankConstraint
    private String email;
    @NotBlankConstraint
    private String password;
    private Boolean loginFirstTime;
    private String tokenType;
    private String accessToken;
    private Integer roleId;
}
