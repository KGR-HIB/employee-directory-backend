package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Skill;

/**
 * Skill repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ISkillRepository extends IQueryDslBaseRepository<SkillEntity> {

    /**
     * Gat all skills
     *
     * @return List of skills
     */
    List<Skill> findAll();
}
