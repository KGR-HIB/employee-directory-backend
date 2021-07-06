package com.hiberus.employee.directory.service;

import java.util.List;
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
    @Override
    public List<Role> findAll() {
        return this.repository.findAll();
    }

}
