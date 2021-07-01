package com.hiberus.employee.directory.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.IEmployeRepository;
import com.hiberus.employee.directory.service.common.BaseService;

/**
 * EmployeService.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Lazy
@Service
public class EmployeService extends BaseService<EmployeeEntity, IEmployeRepository> implements IEmployeService {

    /**
     * Constructor.
     * 
     * @param repository IEmployeRepository
     */
    public EmployeService(IEmployeRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(EmployeeEntity employeeEntity) {
        this.repository.create(employeeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(EmployeeEntity employeeEntity) {
        this.repository.updateValues(employeeEntity);

    }

}
