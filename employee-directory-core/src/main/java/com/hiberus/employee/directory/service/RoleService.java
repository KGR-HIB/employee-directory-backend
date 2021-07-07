package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hiberus.employee.directory.entity.RoleEntity;
import com.hiberus.employee.directory.repository.IRoleRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Role;

/**
 * RoleService.
 * 
 * @author acachiguango on 06/07/2021
 * @version 1.0
 */
@Lazy
@Service
public class RoleService extends BaseService<RoleEntity, IRoleRepository> implements IRoleService {

    /**
     * Constructor.
     * 
     * @param repository IRoleRepository
     */
    public RoleService(IRoleRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return this.repository.findAll();
    }

}
