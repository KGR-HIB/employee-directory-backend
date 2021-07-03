package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.repository.IDepartmentRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Department;

/**
 * Department service implementation
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class DepartmentService extends BaseService<DepartmentEntity, IDepartmentRepository>
    implements IDepartmentService {

    public DepartmentService(IDepartmentRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }
}
