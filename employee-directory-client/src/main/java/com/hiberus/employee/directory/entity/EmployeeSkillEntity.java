package com.hiberus.employee.directory.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.hiberus.employee.directory.entity.common.AbstractAuditable;
import com.hiberus.employee.directory.entity.id.EmployeeSkillId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for employee skills
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTEMPSKI")
public class EmployeeSkillEntity extends AbstractAuditable {

    @EmbeddedId
    private EmployeeSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_ID", referencedColumnName = "SKILL_ID", insertable = false, updatable = false)
    private SkillEntity skill;
}
