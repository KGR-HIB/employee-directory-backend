package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeSkillEntity.employeeSkillEntity;
import static com.hiberus.employee.directory.entity.QSkillEntity.skillEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeSkillEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Skill;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;


/**
 * Repository implementation for EmployeeSkill
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeSkillRepository extends JPAQueryDslBaseRepository<EmployeeSkillEntity> implements IEmployeeSkillRepository {

    public EmployeeSkillRepository() {
        super(EmployeeSkillEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Skill> findByEmployeeId(Integer employeeId) {
        JPQLQuery<Skill> jpqlQuery = from(employeeSkillEntity)
            .select(bean(Skill.class, skillEntity.id, skillEntity.name))
            .join(employeeSkillEntity.skill, skillEntity);

        jpqlQuery.where(
            skillEntity.status.eq(Boolean.TRUE)
            .and(employeeSkillEntity.status.eq(Boolean.TRUE))
            .and(employeeSkillEntity.employeeId.eq(employeeId))
        );

        return jpqlQuery.fetch();
    }
}
