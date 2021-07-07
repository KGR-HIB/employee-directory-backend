package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.IProjectRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Project;

/**
 * Project service implements
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
@Transactional
public class ProjectService extends BaseService<ProjectEntity, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createByName(List<ProjectEntity> projects, Integer createdByUser) {
        this.repository.createByName(projects, createdByUser);
    }
}
