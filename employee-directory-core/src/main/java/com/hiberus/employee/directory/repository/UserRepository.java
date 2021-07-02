package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.UserEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer createOrUpdate(UserEntity userEntity, Integer createdByUser) {
        if (null == userEntity.getId()) {
            // Encriptar la contraseña
            userEntity.setEmail(userEntity.getEmail().trim());
            userEntity.setCreateDate(DateUtil.currentDate());
            userEntity.setLoginFirstTime(Boolean.FALSE);
            userEntity.setCreatedByUser(createdByUser);
            userEntity.setStatus(Boolean.TRUE);
            this.save(userEntity);
            return userEntity.getId();
        }
        this.updateValues(userEntity, createdByUser);
        return userEntity.getId();
    }

    /**
     * Actualiza datos del usuario.
     * 
     * @author acahciguango on 01/07/2021
     * @param entity UserEntity
     * @param createdByUser id usuario
     */
    private void updateValues(UserEntity entity, Integer createdByUser) {
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.id.eq(entity.getId()));
        where.and(userEntity.status.eq(Boolean.TRUE));
        update(userEntity).where(where).set(userEntity.password, entity.getPassword())
            .set(userEntity.roleId, entity.getRoleId()).set(userEntity.lastModifiedByUser, createdByUser)
            .set(userEntity.lastModifiedDate, DateUtil.currentDate()).execute();
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
