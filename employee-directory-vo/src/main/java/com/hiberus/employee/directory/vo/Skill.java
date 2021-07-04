package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO response for Skill
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    /**
     * Skill id
     */
    private Integer id;

    /**
     * Skill's name
     */
    private String name;

    /**
     * Skill's status
     */
    private Boolean status;
}
