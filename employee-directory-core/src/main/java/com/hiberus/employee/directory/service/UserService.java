package com.hiberus.employee.directory.service;

import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.IUserRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Lazy
@Service
public class UserService extends BaseService<UserEntity, IUserRepository> implements IUserService {
    public UserService(IUserRepository repository) {
        super(repository);
    }
}
