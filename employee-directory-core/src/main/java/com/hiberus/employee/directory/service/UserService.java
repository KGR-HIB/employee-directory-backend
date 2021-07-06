package com.hiberus.employee.directory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.IEmployeeRepository;
import com.hiberus.employee.directory.repository.IRoleFunctionalityRepository;
import com.hiberus.employee.directory.repository.IUserRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.User;

/**
 * UserService.
 * 
 * @author Kruger on 02/07/2021
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class UserService extends BaseService<UserEntity, IUserRepository> implements IUserService {

    @Lazy
    @Autowired
    private IRoleFunctionalityRepository roleFunRepository;

    @Lazy
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Lazy
    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    /**
     * Constructor.
     * 
     * @param repository IUserRepository
     */
    public UserService(IUserRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public User login(User request) {
        User user = this.repository.login(request);
        if (null != user && this.argon2PasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.setEmploye(this.employeeRepository.findByUserId(user.getId()));
            user.getRole().setFunctionalities(this.roleFunRepository.findByRoleId(user.getRoleId()));
            user.setPassword(null);
            return user;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Boolean existsByMail(String email) {
        return this.repository.existsByMail(email);
    }
}
