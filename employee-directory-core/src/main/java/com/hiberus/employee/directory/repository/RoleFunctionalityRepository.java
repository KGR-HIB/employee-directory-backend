package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QRoleFunctionalityEntity.roleFunctionalityEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.QFunctionalityEntity;
import com.hiberus.employee.directory.entity.RoleFunctionalityEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Functionality;
import com.hiberus.employee.directory.vo.RoleFunctionality;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

/**
 * RoleFunctionalityRepository.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
@Lazy
@Repository
public class RoleFunctionalityRepository extends JPAQueryDslBaseRepository<RoleFunctionalityEntity>
    implements IRoleFunctionalityRepository {

    /**
     * Constructor.
     */
    public RoleFunctionalityRepository() {
        super(RoleFunctionalityEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleFunctionality> findByRoleId(Integer roleId) {
        QFunctionalityEntity qFunctionalityEntity = QFunctionalityEntity.functionalityEntity;
        JPQLQuery<RoleFunctionality> query = from(roleFunctionalityEntity).select(bean(RoleFunctionality.class,
            roleFunctionalityEntity.id, Projections.bean(Functionality.class, qFunctionalityEntity.id,
                qFunctionalityEntity.code, qFunctionalityEntity.description).as("functionality")));
        query.innerJoin(roleFunctionalityEntity.functionality, qFunctionalityEntity);
        BooleanBuilder where = new BooleanBuilder();
        where.and(roleFunctionalityEntity.roleId.eq(roleId));
        where.and(roleFunctionalityEntity.status.eq(Boolean.TRUE));
        query.where(where);
        return query.fetch();
    }
}
