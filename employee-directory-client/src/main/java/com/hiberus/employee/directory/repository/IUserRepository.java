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

    /**
     * Crear o actualiza usuario.
     * 
     * @author acahciguango on 01/07/2021
     * @param userEntity UserEntity
     * @param createdByUser id
     * @return id usuario
     */
    Integer createOrUpdate(UserEntity userEntity, Integer createdByUser);

    /**
     * Verifica si ya existe un usuario por correo.
     * 
     * @author acachiguango on 02/07/2021
     * @param email correo
     * @return Boolean
     */
    Boolean existsByMail(String email);
}
