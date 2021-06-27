package com.hiberus.employee.directory.entity.id;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Id for Employee skill
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EmployeeSkillId implements Serializable {

    /**
     * Skill id
     */
    @Column(name = "SKILL_ID")
    private Integer skillId;

    /**
     * Employee id
     */
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;
}
