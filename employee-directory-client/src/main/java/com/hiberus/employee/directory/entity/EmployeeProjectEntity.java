package com.hiberus.employee.directory.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.hiberus.employee.directory.entity.id.EmployeeProjectId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Employee - project
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTEMPPRO")
public class EmployeeProjectEntity {

    @EmbeddedId
    private EmployeeProjectId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", insertable = false, updatable = false)
    private ProjectEntity project;
}
