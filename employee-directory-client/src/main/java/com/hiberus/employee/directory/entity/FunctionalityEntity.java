package com.hiberus.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.hiberus.employee.directory.entity.common.AbstractAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Functionality
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTFUNCTIONALITY")
public class FunctionalityEntity extends AbstractAuditable {

    /**
     * Functionality id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUNCTIONALITY_ID", nullable = false, updatable = false)
    private Integer functionalityId;

    /**
     * Code of the functionality
     */
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    /**
     * Description of the functionality
     */
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
