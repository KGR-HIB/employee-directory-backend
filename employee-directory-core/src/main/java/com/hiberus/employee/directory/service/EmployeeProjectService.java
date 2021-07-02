package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.hiberus.employee.directory.entity.EmployeeProjectEntity;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.repository.IEmployeeProjectRepository;
import com.hiberus.employee.directory.repository.IProjectRepository;
import com.hiberus.employee.directory.service.common.BaseService;

/**
 * IEmployeeProjectService.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
@Lazy
@Service
public class EmployeeProjectService extends BaseService<EmployeeProjectEntity, IEmployeeProjectRepository>
    implements IEmployeeProjectService {

    private IProjectRepository projectRepository;

    /**
     * Constructor.
     */
    public EmployeeProjectService(IEmployeeProjectRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createByName(List<ProjectEntity> projects, Integer employeeId, Integer createdByUser) {
        List<Integer> projectIds = this.projectRepository.createByName(projects, createdByUser);
        this.repository.create(projectIds, employeeId, createdByUser);
        this.repository.disableEmployeeProject(projectIds, employeeId, createdByUser);

    }

}
