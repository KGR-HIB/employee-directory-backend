package com.hiberus.employee.directory.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.hiberus.employee.directory.entity.common.AbstractAuditable;
import com.hiberus.employee.directory.entity.id.RoleFunctionalityId;
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
public class RoleFunctionalityEntity extends AbstractAuditable {

    /**
     * Id for Role - Functionality
     */
    @EmbeddedId
    private RoleFunctionalityId id;

    /**
     * Functionality
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCTIONALITY_ID", referencedColumnName = "FUNCTIONALITY_ID", insertable = false, updatable = false)
    private FunctionalityEntity functionality;
}
