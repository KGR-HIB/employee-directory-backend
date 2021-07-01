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
 * Entity for Role Functionality
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTROLFUN")
public class RoleFunctionalityEntity extends AbstractBaseAuditable {

    /**
     * Id for Role - Functionality
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLFUN_ID")
    private Integer id;

    /**
     * Functionality
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCTIONALITY_ID", referencedColumnName = "FUNCTIONALITY_ID", insertable = false, updatable = false)
    private FunctionalityEntity functionality;
}
