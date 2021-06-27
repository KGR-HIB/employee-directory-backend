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
 * Entity for Position
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTPOSITION")
public class PositionEntity extends AbstractAuditable {

    /**
     * Position id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITION_ID", nullable = false, updatable = false)
    private Integer positionId;

    /**
     * Position’s name
     */
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;
}
