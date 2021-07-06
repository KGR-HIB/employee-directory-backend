package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vo response for Position
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    /**
     * Position id
     */
    private Integer id;

    /**
     * Position's name
     */
    private String name;
}
