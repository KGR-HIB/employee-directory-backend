package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.RoleEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Role;

/**
 * IRoleRepository.
 * 
 * @author acachiguango on 06/07/2021
 * @version 1.0
 */
public interface IRoleRepository extends IQueryDslBaseRepository<RoleEntity> {
    /**
     * Get all roles.
     * 
     * @author acachiguango on 06/07/2021
     * @return List Role
     */
    List<Role> findAll();
}
