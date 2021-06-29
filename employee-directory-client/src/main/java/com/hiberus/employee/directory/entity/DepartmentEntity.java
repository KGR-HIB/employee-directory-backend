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
 * Entity for Department
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTDEPARTMENT")
public class DepartmentEntity extends AbstractBaseAuditable {

    /**
     * Department id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", nullable = false, updatable = false)
    private Long id;

    /**
     * Department’s name
     */
    @Column(name = "NAME")
    private String name;
}
