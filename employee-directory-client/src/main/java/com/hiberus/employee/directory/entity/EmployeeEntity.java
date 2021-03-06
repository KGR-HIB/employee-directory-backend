package com.hiberus.employee.directory.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.hiberus.employee.directory.entity.common.AbstractBaseAuditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for Employee
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "HHTEDTEMPLOYEE")
public class EmployeeEntity extends AbstractBaseAuditable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Employee id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", nullable = false, updatable = false)
    private Integer id;

    /**
     * Employee names
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Last name of the employee
     */
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    /**
     * Employee phone
     */
    @Column(name = "PHONE", nullable = false)
    private String phone;

    /**
     * Employee photo folder address
     */
    @Column(name = "PHOTO_PATH")
    private String photoPath;

    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "POSITION_ID")
    private Integer positionId;

    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;

    @Column(name = "IMMEDIATE_CHIEF_ID")
    private Integer immediateChiefId;

    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * City where the employee lives
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID", insertable = false, updatable = false)
    private CityEntity city;

    /**
     * Position of the employee
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID", referencedColumnName = "POSITION_ID", insertable = false, updatable = false)
    private PositionEntity position;

    /**
     * Department to which the employee belongs
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID", insertable = false, updatable = false)
    private DepartmentEntity department;

    /**
     * Immediate chief of the employee
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMMEDIATE_CHIEF_ID", referencedColumnName = "EMPLOYEE_ID", insertable = false,
        updatable = false)
    private EmployeeEntity immediateChief;

    /**
     * User
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;

    /**
     * Skills the employee has
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Collection<EmployeeSkillEntity> skills;

    /**
     * Certification the employee has
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Collection<EmployeeCertificationEntity> certifications;

    /**
     * Projects the employee has
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Collection<EmployeeProjectEntity> projects;
}
