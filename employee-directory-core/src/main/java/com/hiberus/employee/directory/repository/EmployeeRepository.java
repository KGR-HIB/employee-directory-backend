package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeEntity.employeeEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.QCityEntity;
import com.hiberus.employee.directory.entity.QDepartmentEntity;
import com.hiberus.employee.directory.entity.QEmployeeCertificationEntity;
import com.hiberus.employee.directory.entity.QEmployeeEntity;
import com.hiberus.employee.directory.entity.QEmployeeProjectEntity;
import com.hiberus.employee.directory.entity.QEmployeeSkillEntity;
import com.hiberus.employee.directory.entity.QPositionEntity;
import com.hiberus.employee.directory.entity.QUserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.City;
import com.hiberus.employee.directory.vo.Department;
import com.hiberus.employee.directory.vo.Employee;
import com.hiberus.employee.directory.vo.EmployeeFiltersRequest;
import com.hiberus.employee.directory.vo.Position;
import com.hiberus.employee.directory.vo.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

/**
 * EmployeRepository.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeRepository extends JPAQueryDslBaseRepository<EmployeeEntity> implements IEmployeeRepository {

    /**
     * Constructor.
     */
    public EmployeeRepository() {
        super(EmployeeEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(EmployeeEntity employeeEntity) {
        employeeEntity.setCreateDate(DateUtil.currentDate());
        employeeEntity.setStatus(Boolean.TRUE);
        employeeEntity.setCity(null);
        employeeEntity.setPosition(null);
        employeeEntity.setDepartment(null);
        employeeEntity.setUser(null);
        this.save(employeeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateValues(EmployeeEntity employeeEntity) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeEntity.id.eq(employeeEntity.getId()));
        where.and(qEmployeeEntity.status.eq(Boolean.TRUE));
        update(qEmployeeEntity).where(where).set(qEmployeeEntity.name, employeeEntity.getName())
            .set(qEmployeeEntity.lastName, employeeEntity.getLastName())
            .set(qEmployeeEntity.phone, employeeEntity.getPhone())
            .set(qEmployeeEntity.photoPath, employeeEntity.getPhotoPath())
            .set(qEmployeeEntity.cityId, employeeEntity.getCityId())
            .set(qEmployeeEntity.positionId, employeeEntity.getPositionId())
            .set(qEmployeeEntity.departmentId, employeeEntity.getDepartmentId())
            .set(qEmployeeEntity.immediateChiefId, employeeEntity.getImmediateChiefId())
            .set(qEmployeeEntity.lastModifiedByUser, employeeEntity.getLastModifiedByUser())
            .set(qEmployeeEntity.lastModifiedDate, DateUtil.currentDate()).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> findByNamesAndEmail(String query) {
        boolean isNumeric = query.matches("[+-]?\\d*(\\.\\d+)?");
        QUserEntity qUserEntity = QUserEntity.userEntity;
        JPQLQuery<Employee> jpaQuery = from(employeeEntity)
            .select(bean(Employee.class, employeeEntity.id, employeeEntity.name, employeeEntity.lastName));

        // If query is Id employee
        if (isNumeric) {
            jpaQuery.where(employeeEntity.id.eq(Integer.valueOf(query)));
            return jpaQuery.fetch();
        }

        // Otherwise search in name, lastName and email
        jpaQuery.join(employeeEntity.user, qUserEntity);
        jpaQuery.where(employeeEntity.status.eq(Boolean.TRUE).and(employeeEntity.name.containsIgnoreCase(query)
            .or(employeeEntity.lastName.containsIgnoreCase(query)).or(qUserEntity.email.containsIgnoreCase(query))));

        jpaQuery.orderBy(employeeEntity.name.asc(), employeeEntity.lastName.asc());

        return jpaQuery.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee findEmployeeMainInformationById(Integer id) {
        QCityEntity qCityEntity = QCityEntity.cityEntity;
        QDepartmentEntity qDepartmentEntity = QDepartmentEntity.departmentEntity;
        QPositionEntity qPositionEntity = QPositionEntity.positionEntity;
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QEmployeeEntity qEmployeeChief = new QEmployeeEntity("chief");

        JPQLQuery<Employee> jpqlQuery = from(employeeEntity)
            .select(bean(Employee.class, employeeEntity.id, employeeEntity.name, employeeEntity.lastName,
                employeeEntity.phone, bean(City.class, qCityEntity.id, qCityEntity.name).as("city"),
                bean(Department.class, qDepartmentEntity.id, qDepartmentEntity.name).as("department"),
                bean(Position.class, qPositionEntity.id, qPositionEntity.name).as("position"),
                bean(User.class, qUserEntity.id, qUserEntity.email).as("user"),
                bean(Employee.class, qEmployeeChief.id, qEmployeeChief.name, qEmployeeChief.lastName)
                    .as("immediateChief")));

        jpqlQuery.leftJoin(employeeEntity.city, qCityEntity).leftJoin(employeeEntity.department, qDepartmentEntity)
            .leftJoin(employeeEntity.position, qPositionEntity).leftJoin(employeeEntity.user, qUserEntity)
            .leftJoin(qEmployeeChief).on(employeeEntity.immediateChiefId.eq(qEmployeeChief.id));

        jpqlQuery.where(employeeEntity.id.eq(id).and(employeeEntity.status.eq(Boolean.TRUE)));

        return jpqlQuery.fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Employee> pageByFilters(Pageable pageable, String query,
        EmployeeFiltersRequest employeeFiltersRequest) {

        final BooleanExpression booleanExpression = this.getRestrictionByFilter(query, employeeFiltersRequest);

        QUserEntity qUserEntity = QUserEntity.userEntity;
        QPositionEntity qPositionEntity = QPositionEntity.positionEntity;
        QDepartmentEntity qDepartmentEntity = QDepartmentEntity.departmentEntity;

        JPQLQuery<Employee> jpqlQuery = from(employeeEntity)
            .select(bean(Employee.class, employeeEntity.id, employeeEntity.name, employeeEntity.lastName,
                employeeEntity.phone, bean(User.class, qUserEntity.email).as("user"),
                bean(Position.class, qPositionEntity.name).as("position"),
                bean(Department.class, qDepartmentEntity.name).as("department")))
            .leftJoin(employeeEntity.position, qPositionEntity).leftJoin(employeeEntity.department, qDepartmentEntity);

        this.addJoinsByFilters(jpqlQuery, employeeFiltersRequest);

        jpqlQuery.where(booleanExpression).groupBy(employeeEntity.id);

        jpqlQuery.orderBy(employeeEntity.name.asc(), employeeEntity.lastName.asc());

        return this.findPagedData(jpqlQuery, pageable);
    }

    /**
     * Add joins to jpa query to paging
     *
     * @author bcueva
     * @param jpqlQuery Query
     * @param employeeFiltersRequest Advanced filters
     */
    private void addJoinsByFilters(JPQLQuery<Employee> jpqlQuery, EmployeeFiltersRequest employeeFiltersRequest) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QEmployeeProjectEntity qEmployeeProjectEntity = QEmployeeProjectEntity.employeeProjectEntity;
        QEmployeeSkillEntity qEmployeeSkillEntity = QEmployeeSkillEntity.employeeSkillEntity;
        QEmployeeCertificationEntity qEmployeeCertificationEntity =
            QEmployeeCertificationEntity.employeeCertificationEntity;

        jpqlQuery.leftJoin(employeeEntity.user, qUserEntity);

        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getProjects())) {
            jpqlQuery.leftJoin(employeeEntity.projects, qEmployeeProjectEntity);
        }

        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getSkills())) {
            jpqlQuery.leftJoin(employeeEntity.skills, qEmployeeSkillEntity);
        }

        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getCertifications())) {
            jpqlQuery.leftJoin(employeeEntity.certifications, qEmployeeCertificationEntity);
        }
    }

    /**
     * Create restrictions by filter
     *
     * @author bcueva
     * @param query Query by name, lastName and email
     * @param employeeFiltersRequest Advanced filter
     * @return Employee page
     */
    private BooleanExpression getRestrictionByFilter(String query, EmployeeFiltersRequest employeeFiltersRequest) {
        BooleanExpression where = employeeEntity.status.eq(Boolean.TRUE);
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QEmployeeProjectEntity qEmployeeProjectEntity = QEmployeeProjectEntity.employeeProjectEntity;
        QEmployeeSkillEntity qEmployeeSkillEntity = QEmployeeSkillEntity.employeeSkillEntity;
        QEmployeeCertificationEntity qEmployeeCertificationEntity =
            QEmployeeCertificationEntity.employeeCertificationEntity;

        // Filter by name, lastName and email
        if (!ObjectUtils.isEmpty(query)) {
            where = where.and(employeeEntity.name.containsIgnoreCase(query)
                .or(employeeEntity.lastName.containsIgnoreCase(query)).or(qUserEntity.email.containsIgnoreCase(query)));
        }

        // Filter by position
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getPositions())) {
            where = where.and(employeeEntity.positionId.in(employeeFiltersRequest.getPositions()));
        }

        // Filter by department
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getDepartments())) {
            where = where.and(employeeEntity.departmentId.in(employeeFiltersRequest.getDepartments()));
        }

        // Filter by city
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getCities())) {
            where = where.and(employeeEntity.cityId.in(employeeFiltersRequest.getCities()));
        }

        // Filter by project
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getProjects())) {
            where = where.and(qEmployeeProjectEntity.status.eq(Boolean.TRUE)
                .and(qEmployeeProjectEntity.projectId.in(employeeFiltersRequest.getProjects())));
        }

        // Filter by skill
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getSkills())) {
            where = where.and(qEmployeeSkillEntity.status.eq(Boolean.TRUE)
                .and(qEmployeeSkillEntity.skillId.in(employeeFiltersRequest.getSkills())));
        }

        // Filter by certification
        if (!CollectionUtils.isEmpty(employeeFiltersRequest.getCertifications())) {
            where = where.and(qEmployeeCertificationEntity.status.eq(Boolean.TRUE)
                .and(qEmployeeCertificationEntity.certificationId.in(employeeFiltersRequest.getCertifications())));
        }

        return where;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee findByUserId(Integer userId) {
        JPQLQuery<Employee> query = from(employeeEntity).select(Projections.bean(Employee.class, employeeEntity.id,
            employeeEntity.userId, employeeEntity.name, employeeEntity.lastName));
        BooleanBuilder where = new BooleanBuilder();
        where.and(employeeEntity.userId.eq(userId));
        where.and(employeeEntity.status.eq(Boolean.TRUE));
        query.where(where);
        return query.fetchFirst();
    }

}
