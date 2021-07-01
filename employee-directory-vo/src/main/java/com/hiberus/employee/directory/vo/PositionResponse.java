package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Vo response for Position
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class PositionResponse {

    /**
     * Position id
     */
    private Integer id;

    /**
     * Position's name
     */
    private String name;
}
