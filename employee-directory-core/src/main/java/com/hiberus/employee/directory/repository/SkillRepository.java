package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QSkillEntity.skillEntity;

import java.util.List;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<SkillEntity> findAll() {
        return from(skillEntity)
            .where(skillEntity.status.eq(Boolean.TRUE))
            .fetch();
    }
}
