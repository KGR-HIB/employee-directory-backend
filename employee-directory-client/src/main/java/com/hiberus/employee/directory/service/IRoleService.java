package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.vo.Role;

/**
 * IRoleService.java
 * 
 * @author acachiguango on 06/07/2021
 * @version 1.0
 * @since 1.0.0
 */
public interface IRoleService {
    /**
     * Get all roles.
     * 
     * @author acachiguango on 06/07/2021
     * @return List Role
     */
    List<Role> findAll();
}
