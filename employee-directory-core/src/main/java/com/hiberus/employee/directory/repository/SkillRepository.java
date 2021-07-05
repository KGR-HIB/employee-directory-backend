package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QSkillEntity.skillEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.hiberus.employee.directory.vo.Skill;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * Skill repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class SkillRepository extends JPAQueryDslBaseRepository<SkillEntity> implements ISkillRepository {

    public SkillRepository() {
        super(SkillEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Skill> findAll() {
        return from(skillEntity).select(bean(Skill.class, skillEntity.id, skillEntity.name))
            .where(skillEntity.status.eq(Boolean.TRUE)).fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> createByName(List<SkillEntity> skills, Integer createdByUser) {
        List<Integer> skillIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(skills)) {
            return skillIds;
        }
        for (SkillEntity skillEntity : skills) {
            if (null == skillEntity.getId()) {
                skillIds.add(this.validateByName(skillEntity, createdByUser));
            } else {
                skillIds.add(skillEntity.getId());
            }
        }
        return skillIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(SkillEntity skillEntity) {
        skillEntity.setName(NameUtil.clearAccents(skillEntity.getName()));
        skillEntity.setCreateDate(DateUtil.currentDate());
        skillEntity.setStatus(Boolean.TRUE);
        this.save(skillEntity);
    }

    /**
     * Validate by name.
     * 
     * @author acachiguango on 02/07/2021
     * @param skillEntity SkillEntity
     * @param createdByUser userId
     * @return skillId
     */
    private Integer validateByName(SkillEntity skillEntity, Integer createdByUser) {
        SkillEntity entity = this.findByName(skillEntity.getName());
        if (null == entity) {
            skillEntity.setCreatedByUser(createdByUser);
            this.create(skillEntity);
            return skillEntity.getId();
        }
        return entity.getId();
    }

    /**
     * Get tag by name.
     * 
     * @author acachiguango on 01/07/2021
     * @param name tag name
     * @return SkillEntity
     */
    private SkillEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(skillEntity.name.equalsIgnoreCase(value));
        where.and(skillEntity.status.eq(Boolean.TRUE));
        JPQLQuery<SkillEntity> query = from(skillEntity).select(bean(SkillEntity.class, skillEntity.id));
        query.where(where);
        return query.fetchFirst();
    }
}
