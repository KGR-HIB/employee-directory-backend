package com.hiberus.employee.directory.service;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.User;

/**
 * User service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IUserService extends IBaseService<UserEntity> {

    /**
     * Login usuario.
     * 
     * @author acachiguango on 30/06/2021
     * @param request Login
     * @return User
     */
    User login(User request);

    /**
     * Verifica si ya existe un usuario por correo.
     * 
     * @author acachiguango on 02/07/2021
     * @param email correo
     * @return Boolean
     */
    Boolean existsByMail(String email);

}
