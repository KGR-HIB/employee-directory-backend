package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class UserRepository extends JPAQueryDslRepository<UserEntity> implements IUserRepository {

    public UserRepository() {
        super(UserEntity.class);
    }
}
