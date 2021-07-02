package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QDepartmentEntity.departmentEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * Department repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class DepartmentRepository extends JPAQueryDslBaseRepository<DepartmentEntity> implements IDepartmentRepository {

    public DepartmentRepository() {
        super(DepartmentEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DepartmentEntity> findAll() {
        return from(departmentEntity).where(departmentEntity.status.eq(Boolean.TRUE)).fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer createByName(DepartmentEntity departmentEntity, Integer createdByUser) {
        if (null == departmentEntity.getId()) {
            DepartmentEntity entity = this.findByName(departmentEntity.getName());
            if (null != entity) {
                return entity.getId();
            }
            departmentEntity.setCreatedByUser(createdByUser);
            this.create(departmentEntity);
        }
        return departmentEntity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(DepartmentEntity departmentEntity) {
        departmentEntity.setName(NameUtil.clearAccents(departmentEntity.getName()));
        departmentEntity.setCreateDate(DateUtil.currentDate());
        departmentEntity.setStatus(Boolean.TRUE);
        this.save(departmentEntity);

    }

    /**
     * Obtiene departamento por nombre.
     * 
     * @author acachiguango on 01/07/2021
     * @param name nombre
     * @return DepartmentEntity
     */
    private DepartmentEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(departmentEntity.name.equalsIgnoreCase(value));
        where.and(departmentEntity.status.eq(Boolean.TRUE));
        JPQLQuery<DepartmentEntity> query =
            from(departmentEntity).select(bean(DepartmentEntity.class, departmentEntity.id, departmentEntity.name));
        query.where(where);
        return query.fetchFirst();
    }
}
