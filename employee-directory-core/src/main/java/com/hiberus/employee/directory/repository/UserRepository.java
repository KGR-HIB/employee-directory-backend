package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslRepository;

public class UserRepository extends JPAQueryDslRepository<UserEntity> {

    public UserRepository() {
        super(UserEntity.class);
    }
}
