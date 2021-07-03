package com.hiberus.employee.directory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * VO response for Project
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Project {

    /**
     * Project id
     */
    private Integer id;

    /**
     * Project's name
     */
    private String name;
    /**
     * Project's status
     */
    private Boolean status;
}
