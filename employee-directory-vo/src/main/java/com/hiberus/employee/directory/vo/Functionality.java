package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Functionality.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Functionality {
    private Integer id;
    private String code;
    private String description;
}
