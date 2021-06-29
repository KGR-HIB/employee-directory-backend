package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCityEntity.cityEntity;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * City repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class CityRepository extends JPAQueryDslBaseRepository<CityEntity> implements
    ICityRepository {

    public CityRepository() {
        super(CityEntity.class);
    }

    @Override
    public List<CityEntity> findAll() {
        return from(cityEntity)
            .where(cityEntity.status.eq(Boolean.TRUE))
            .fetch();
    }
}
