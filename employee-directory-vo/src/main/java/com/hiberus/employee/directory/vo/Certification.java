package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO response Certification
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class Certification {

    /**
     * Id of certification
     */
    private Integer id;

    /**
     * Name of certification
     */
    private String name;

}
