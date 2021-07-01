package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO response for Skill
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class SkillResponse {

    /**
     * Skill id
     */
    private Integer id;

    /**
     * Skill's name
     */
    private String name;
}
