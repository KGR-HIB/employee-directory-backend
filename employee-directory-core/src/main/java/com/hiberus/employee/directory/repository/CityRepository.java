package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCityEntity.cityEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * City repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class CityRepository extends JPAQueryDslBaseRepository<CityEntity> implements ICityRepository {

    public CityRepository() {
        super(CityEntity.class);
    }

    @Override
    public List<CityEntity> findAll() {
        return from(cityEntity).where(cityEntity.status.eq(Boolean.TRUE)).fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer createByName(CityEntity entity, Integer createdByUser) {
        if (null == entity.getId()) {
            CityEntity city = this.findByName(entity.getName());
            if (null != city) {
                return city.getId();
            }
            entity.setCreatedByUser(createdByUser);
            this.create(entity);
        }
        return entity.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CityEntity cityEntity) {
        cityEntity.setName(NameUtil.clearAccents(cityEntity.getName()));
        cityEntity.setCreateDate(DateUtil.currentDate());
        cityEntity.setStatus(Boolean.TRUE);
        this.save(cityEntity);
    }

    /**
     * Obtiene ciudad por nombre.
     * 
     * @author acachiguango on 01/07/2021
     * @param name nombre
     * @return CityEntity
     */
    private CityEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(cityEntity.name.equalsIgnoreCase(value));
        where.and(cityEntity.status.eq(Boolean.TRUE));
        JPQLQuery<CityEntity> query = from(cityEntity).select(bean(CityEntity.class, cityEntity.id, cityEntity.name));
        query.where(where);
        return query.fetchFirst();
    }

}
