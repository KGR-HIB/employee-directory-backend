package com.hiberus.employee.directory.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO response for Project
 *
 * @author bcueva
 * @version 1.0
 */
@NoArgsConstructor
@Setter
@Getter
public class ProjectResponse {

    /**
     * Project id
     */
    private Long id;

    /**
     * Project's name
     */
    private String name;
}
