package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QEmployeeSkillEntity.employeeSkillEntity;
import static com.hiberus.employee.directory.entity.QSkillEntity.skillEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.EmployeeSkillEntity;
import com.hiberus.employee.directory.entity.QEmployeeSkillEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Skill;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * Repository implementation for EmployeeSkill
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeSkillRepository extends JPAQueryDslBaseRepository<EmployeeSkillEntity>
    implements IEmployeeSkillRepository {

    public EmployeeSkillRepository() {
        super(EmployeeSkillEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Skill> findByEmployeeId(Integer employeeId, Boolean status) {
        JPQLQuery<Skill> query = from(employeeSkillEntity)
            .select(bean(Skill.class, skillEntity.id, skillEntity.name, employeeSkillEntity.status));
        query.join(employeeSkillEntity.skill, skillEntity);
        BooleanBuilder where = new BooleanBuilder();
        where.and(employeeSkillEntity.employeeId.eq(employeeId));
        where.and(skillEntity.status.eq(Boolean.TRUE));
        if (null != status) {
            where.and(employeeSkillEntity.status.eq(status));
        }
        query.where(where);
        return query.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(List<Integer> skillIds, Integer employeeId, Integer createdByUser) {
        if (CollectionUtils.isEmpty(skillIds)) {
            this.updateStatusEmployeeSkill(skillIds, employeeId, createdByUser, Boolean.FALSE);
            return;
        }
        List<Skill> skills = this.findByEmployeeId(employeeId, null);
        List<Integer> create = new ArrayList<>();
        List<Integer> updateActive = new ArrayList<>();
        for (Integer skillId : skillIds) {
            Skill skill = skills.stream().filter(ski -> ski.getId().equals(skillId)).findFirst().orElse(null);
            if (null == skill) {
                create.add(skillId);
            } else if (!skill.getStatus()) {
                updateActive.add(skill.getId());
            }
        }
        // Create tag relationship
        if (!CollectionUtils.isEmpty(create)) {
            for (Integer skillId : create) {
                EmployeeSkillEntity entity = new EmployeeSkillEntity();
                entity.setCreateDate(DateUtil.currentDate());
                entity.setCreatedByUser(createdByUser);
                entity.setEmployeeId(employeeId);
                entity.setSkillId(skillId);
                entity.setStatus(Boolean.TRUE);
                this.save(entity);
            }
        }
        // Update active tag status
        if (!CollectionUtils.isEmpty(updateActive)) {
            this.updateStatusEmployeeSkill(updateActive, employeeId, createdByUser, Boolean.TRUE);
        }
        // Update inactive tag status
        List<Integer> updateInactive = skills.stream().filter(pro -> !skillIds.contains(pro.getId())).map(Skill::getId)
            .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(updateInactive)) {
            this.updateStatusEmployeeSkill(updateInactive, employeeId, createdByUser, Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatusEmployeeSkill(List<Integer> skillIds, Integer employeeId, Integer createdByUser,
        Boolean status) {
        QEmployeeSkillEntity qEmployeeSkillEntity = QEmployeeSkillEntity.employeeSkillEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeSkillEntity.employeeId.eq(employeeId));
        if (!CollectionUtils.isEmpty(skillIds)) {
            where.and(qEmployeeSkillEntity.skillId.in(skillIds));
        }
        update(qEmployeeSkillEntity).where(where).set(qEmployeeSkillEntity.status, status)
            .set(qEmployeeSkillEntity.lastModifiedByUser, createdByUser)
            .set(qEmployeeSkillEntity.lastModifiedDate, DateUtil.currentDate()).execute();
    }
}
