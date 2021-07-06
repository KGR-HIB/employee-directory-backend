package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vo response for Department
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    /**
     * Id of department
     */
    private Integer id;

    /**
     * Name of department
     */
    private String name;
}
