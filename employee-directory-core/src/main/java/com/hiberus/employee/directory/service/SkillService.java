package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.repository.ISkillRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Skill service implements
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class SkillService extends BaseService<SkillEntity, ISkillRepository> implements ISkillService {

    public SkillService(ISkillRepository repository) {
        super(repository);
    }

    @Override
    public List<SkillEntity> findAll() {
        return repository.findAll();
    }
}
