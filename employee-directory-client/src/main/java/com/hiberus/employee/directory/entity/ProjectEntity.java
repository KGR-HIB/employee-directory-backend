package com.hiberus.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.hiberus.employee.directory.entity.common.AbstractBaseAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Project
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTPROJECT")
public class ProjectEntity extends AbstractBaseAuditable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Project id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID", nullable = false, updatable = false)
    private Integer id;

    /**
     * Project name
     */
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
}
