package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.QRoleEntity;
import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Role;
import com.hiberus.employee.directory.vo.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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
    @Lazy
    @Autowired
    private Argon2PasswordEncoder argon2PasswordEncoder;

    /**
     * Constructor.
     */
    public UserRepository() {
        super(UserEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User login(User request) {
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;
        JPQLQuery<User> query = from(userEntity).select(bean(User.class, userEntity.id, userEntity.email,
            userEntity.loginFirstTime, userEntity.roleId, userEntity.password,
            Projections.bean(Role.class, qRoleEntity.id, qRoleEntity.name, qRoleEntity.code).as("role")));
        query.innerJoin(userEntity.role, qRoleEntity);
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.email.eq(request.getEmail()));
        where.and(userEntity.status.eq(Boolean.TRUE));
        query.where(where);
        return query.fetchFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer createOrUpdate(UserEntity userEntity, Integer createdByUser) {
        if (null == userEntity.getId()) {
            String password = this.argon2PasswordEncoder.encode(userEntity.getPassword());
            userEntity.setPassword(password);
            userEntity.setEmail(userEntity.getEmail().trim());
            userEntity.setCreateDate(DateUtil.currentDate());
            userEntity.setLoginFirstTime(Boolean.FALSE);
            userEntity.setCreatedByUser(createdByUser);
            userEntity.setStatus(Boolean.TRUE);
            this.save(userEntity);
            return userEntity.getId();
        }
        this.updateRole(userEntity, createdByUser);
        this.updatePassword(userEntity);
        return userEntity.getId();
    }

    /**
     * Updates user data.
     * 
     * @author acahciguango on 01/07/2021
     * @param entity UserEntity
     * @param createdByUser user id
     */
    private void updateRole(UserEntity entity, Integer createdByUser) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.id.eq(entity.getId()));
        where.and(userEntity.status.eq(Boolean.TRUE));
        update(userEntity).where(where).set(userEntity.roleId, entity.getRoleId())
            .set(userEntity.lastModifiedByUser, createdByUser).set(userEntity.lastModifiedDate, DateUtil.currentDate())
            .execute();
    }

    /**
     * Update password.
     * 
     * @author acachiguango on 05/07/2021
     * @param entity UserEntity
     * @param createdByUser user id
     */
    private void updatePassword(UserEntity entity) {
        if (StringUtils.isNotBlank(entity.getPassword())) {
            String password = this.argon2PasswordEncoder.encode(entity.getPassword());
            BooleanBuilder where = new BooleanBuilder();
            where.and(userEntity.id.eq(entity.getId()));
            where.and(userEntity.status.eq(Boolean.TRUE));
            update(userEntity).where(where).set(userEntity.password, password).execute();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsByMail(String email) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.email.eq(email.trim()));
        where.and(userEntity.status.eq(Boolean.TRUE));
        JPQLQuery<User> query = from(userEntity).select(bean(User.class, userEntity.id));
        query.where(where);
        User user = query.fetchFirst();
        if (null == user) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
