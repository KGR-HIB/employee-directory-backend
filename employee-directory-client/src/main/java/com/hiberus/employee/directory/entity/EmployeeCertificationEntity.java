package com.hiberus.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.hiberus.employee.directory.entity.common.AbstractBaseAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Employee certification
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTEMPCER")
public class EmployeeCertificationEntity extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPCER_ID")
    private Integer id;

    /**
     * Id of employee
     */
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Column(name = "CERTIFICATION_ID")
    private Integer certificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CERTIFICATION_ID", referencedColumnName = "CERTIFICATION_ID", insertable = false,
        updatable = false)
    private CertificationEntity certification;
}
