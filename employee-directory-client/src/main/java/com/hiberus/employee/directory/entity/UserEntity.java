package com.hiberus.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.hiberus.employee.directory.entity.common.AbstractBaseAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for User
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HHTEDTUSER")
public class UserEntity extends AbstractBaseAuditable {

    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Integer id;

    /**
     * User’s email address
     */
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * User’s password
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    /**
     * True if the user has already login the system for the first time, false otherwise
     */
    @Column(name = "LOGIN_FIRST_TIME", columnDefinition = "tinyint default 0")
    private Boolean loginFirstTime;

    /**
     * Role
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", insertable = false, updatable = false)
    private RoleEntity role;
}
