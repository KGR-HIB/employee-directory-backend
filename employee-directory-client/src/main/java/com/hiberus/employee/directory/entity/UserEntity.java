package com.hiberus.employee.directory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.hiberus.employee.directory.entity.common.AbstractAuditable;
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
@Entity(name = "HHTEDTUSER")
public class UserEntity extends AbstractAuditable {

    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Integer userId;

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
    @Column(name = "LOGIN_FIRST_TIME", nullable = false)
    private Boolean loginFirstTime;

    /**
     * Employee
     */
    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", insertable = false, updatable = false)
    private EmployeeEntity employee;

    /**
     * Role
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", insertable = false, updatable = false)
    private RoleEntity role;
}
