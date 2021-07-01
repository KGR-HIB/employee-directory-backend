package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * User repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class UserRepository extends JPAQueryDslBaseRepository<UserEntity> implements IUserRepository {

    public UserRepository() {
        super(UserEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User login(User request) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.email.eq(request.getEmail()));
        where.and(userEntity.password.eq(request.getPassword()));
        where.and(userEntity.status.eq(Boolean.TRUE));
        JPQLQuery<User> query =
            from(userEntity).select(bean(User.class, userEntity.id, userEntity.email, userEntity.loginFirstTime));
        query.where(where);
        return query.fetchFirst();
    }
}
