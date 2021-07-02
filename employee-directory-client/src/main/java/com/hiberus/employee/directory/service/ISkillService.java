package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Skill;

/**
 * Skill service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ISkillService extends IBaseService<SkillEntity> {

    /**
     * Gat all skills
     *
     * @return List of skills
     */
    List<Skill> findAll();
}
