package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QPositionEntity.positionEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.hiberus.employee.directory.vo.Position;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Position repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class PositionRepository extends JPAQueryDslBaseRepository<PositionEntity> implements IPositionRepository {

    public PositionRepository() {
        super(PositionEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Position> findAll() {
        return from(positionEntity)
            .select(bean(Position.class, positionEntity.id, positionEntity.name))
            .where(positionEntity.status.eq(Boolean.TRUE))
            .fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer createByName(PositionEntity positionEntity, Integer createdByUser) {
        if (null == positionEntity.getId()) {
            PositionEntity entity = this.findByName(positionEntity.getName());
            if (null != entity) {
                return entity.getId();
            }
            positionEntity.setCreatedByUser(createdByUser);
            this.create(positionEntity);
        }
        return positionEntity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(PositionEntity positionEntity) {
        positionEntity.setName(NameUtil.clearAccents(positionEntity.getName()));
        positionEntity.setCreateDate(DateUtil.currentDate());
        positionEntity.setStatus(Boolean.TRUE);
        this.save(positionEntity);

    }

    /**
     * Obtiene cargo por nombre.
     * 
     * @author acachiguango on 01/07/2021
     * @param name nombre
     * @return PositionEntity
     */
    private PositionEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(positionEntity.name.equalsIgnoreCase(value));
        where.and(positionEntity.status.eq(Boolean.TRUE));
        JPQLQuery<PositionEntity> query =
            from(positionEntity).select(bean(PositionEntity.class, positionEntity.id, positionEntity.name));
        query.where(where);
        return query.fetchFirst();
    }
}
