package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hiberus.employee.directory.entity.EmployeeSkillEntity;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.repository.IEmployeeSkillRepository;
import com.hiberus.employee.directory.repository.ISkillRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Skill;

/**
 * EmployeeSkillService.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 */
@Lazy
@Service
@Transactional
public class EmployeeSkillService extends BaseService<EmployeeSkillEntity, IEmployeeSkillRepository>
    implements IEmployeeSkillService {

    @Lazy
    @Autowired
    private ISkillRepository skillRepository;

    /**
     * Constructor.
     * 
     * @param repository IEmployeeSkillRepository
     */
    public EmployeeSkillService(IEmployeeSkillRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Skill> createByName(List<SkillEntity> skills, Integer employeeId, Integer createdByUser) {
        List<Integer> skillIds = this.skillRepository.createByName(skills, createdByUser);
        this.repository.create(skillIds, employeeId, createdByUser);
        return this.repository.findByEmployeeId(employeeId, Boolean.TRUE);
    }
}
