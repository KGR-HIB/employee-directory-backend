package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * User repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class UserRepository extends JPAQueryDslBaseRepository<UserEntity> implements
    IUserRepository {

    public UserRepository() {
        super(UserEntity.class);
    }
}
