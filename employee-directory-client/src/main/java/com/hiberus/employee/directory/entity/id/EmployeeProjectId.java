package com.hiberus.employee.directory.entity.id;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Id for Employee project
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EmployeeProjectId implements Serializable {

    /**
     * Project id
     */
    @Column(name = "PROJECT_ID")
    private Integer projectId;

    /**
     * Employee id
     */
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;
}
