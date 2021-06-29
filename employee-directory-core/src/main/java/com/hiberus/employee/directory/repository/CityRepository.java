package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository
public class CityRepository extends JPAQueryDslRepository<CityEntity> implements ICityRepository {

    public CityRepository() {
        super(CityEntity.class);
    }
}
