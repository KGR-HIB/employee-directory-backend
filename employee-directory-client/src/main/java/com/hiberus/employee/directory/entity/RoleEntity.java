package com.hiberus.employee.directory.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.hiberus.employee.directory.entity.common.AbstractBaseAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Role
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTROLE")
public class RoleEntity extends AbstractBaseAuditable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Role id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", nullable = false, updatable = false)
    private Integer id;

    /**
     * Code of role
     */
    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    /**
     * Role’s name
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Functionalities
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Collection<RoleFunctionalityEntity> functionalities;
}
