package com.hiberus.employee.directory.entity.id;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Id for Employee certification
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class EmployeeCertificationId implements Serializable {

    /**
     * Certification id
     */
    @Column(name = "CERTIFICATION_ID")
    private Integer certificationId;

    /**
     * Employee id
     */
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;
}
