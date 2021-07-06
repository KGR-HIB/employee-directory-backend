package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QUserEntity.userEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import com.hiberus.employee.directory.entity.QRoleEntity;
import com.hiberus.employee.directory.entity.RoleEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Role;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * RoleRepository.
 * 
 * @author acachiguango on 06/07/2021
 * @version 1.0
 */
public class RoleRepository extends JPAQueryDslBaseRepository<RoleEntity> implements IRoleRepository {
    /**
     * Constructor.
     */
    public RoleRepository() {
        super(RoleEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> findAll() {
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;
        JPQLQuery<Role> query =
            from(qRoleEntity).select(bean(Role.class, qRoleEntity.id, qRoleEntity.code, qRoleEntity.name));
        BooleanBuilder where = new BooleanBuilder();
        where.and(userEntity.status.eq(Boolean.TRUE));
        query.where(where);
        return query.fetch();
    }
}
