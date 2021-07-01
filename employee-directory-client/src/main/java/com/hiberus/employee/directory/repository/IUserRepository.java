package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.User;

/**
 * User repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IUserRepository extends IQueryDslBaseRepository<UserEntity> {

    /**
     * Login usuario.
     * 
     * @author acachiguango on 30/06/2021
     * @param request User
     * @return User
     */
    User login(User request);
}
