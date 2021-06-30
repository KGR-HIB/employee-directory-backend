package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.IProjectRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Project service implements
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class ProjectService extends BaseService<ProjectEntity, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public List<ProjectEntity> findAll() {
        return repository.findAll();
    }
}
