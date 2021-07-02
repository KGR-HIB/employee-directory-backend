package com.hiberus.employee.directory.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.IUserRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.User;

@Validated
@Lazy
@Service
public class UserService extends BaseService<UserEntity, IUserRepository> implements IUserService {
    public UserService(IUserRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User login(User request) {
        return this.repository.login(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsByMail(String email) {
        return this.repository.existsByMail(email);
    }
}
