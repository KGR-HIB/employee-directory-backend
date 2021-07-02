package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Vo response for city
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class City {

    /**
     * Id of city
     */
    private Integer id;

    /**
     * Name of city
     */
    private String name;
}
