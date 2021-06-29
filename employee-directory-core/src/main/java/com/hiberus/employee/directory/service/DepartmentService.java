package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.repository.IDepartmentRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Department service implementation
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class DepartmentService extends BaseService<DepartmentEntity, IDepartmentRepository> implements IDepartmentService {

    public DepartmentService(IDepartmentRepository repository) {
        super(repository);
    }

    @Override
    public List<DepartmentEntity> findAll() {
        return repository.findAll();
    }
}
