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
     * User login.
     * 
     * @author acachiguango on 30/06/2021
     * @param request User
     * @return User
     */
    User login(User request);

    /**
     * Create or update user.
     * 
     * @author acahciguango on 01/07/2021
     * @param userEntity UserEntity
     * @param createdByUser id user
     * @return id user
     */
    Integer createOrUpdate(UserEntity userEntity, Integer createdByUser);

    /**
     * Check if there is already a user by mail.
     * 
     * @author acachiguango on 02/07/2021
     * @param email email
     * @return Boolean
     */
    Boolean existsByMail(String email);
}
