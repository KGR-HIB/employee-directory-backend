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

    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 02/07/2021
     * @param skills List SkillEntity
     * @param createdByUser user Id
     * @return List skillIds
     */
    List<Integer> createByName(List<SkillEntity> skills, Integer createdByUser);

    /**
     * Create skill.
     * 
     * @author acachiguango on 02/07/2021
     * @param skillEntity SkillEntity
     */
    void create(SkillEntity skillEntity);
}
