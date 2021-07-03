package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO response Certification
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Certification {

    /**
     * Id of certification
     */
    private Integer id;

    /**
     * Name of certification
     */
    private String name;

    /**
     * State of certification
     */
    private Boolean status;

}
