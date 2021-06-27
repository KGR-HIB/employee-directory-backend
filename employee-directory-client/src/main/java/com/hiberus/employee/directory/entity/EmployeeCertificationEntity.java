package com.hiberus.employee.directory.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.hiberus.employee.directory.entity.common.AbstractAuditable;
import com.hiberus.employee.directory.entity.id.EmployeeCertificationId;
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
public class EmployeeCertificationEntity extends AbstractAuditable {

    @EmbeddedId
    private EmployeeCertificationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CERTIFICATION_ID", referencedColumnName = "CERTIFICATION_ID", insertable = false, updatable = false)
    private CertificationEntity certification;
}
