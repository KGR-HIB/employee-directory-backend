package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Vo response for Department
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class DepartmentResponse {

    /**
     * Id of department
     */
    private Long id;

    /**
     * Name of department
     */
    private String name;
}
