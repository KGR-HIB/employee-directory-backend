package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.IEmployeRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Employe;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employe> findByNamesAndEmail(String query) {
        return this.repository.findByNamesAndEmail(query);
    }

}
