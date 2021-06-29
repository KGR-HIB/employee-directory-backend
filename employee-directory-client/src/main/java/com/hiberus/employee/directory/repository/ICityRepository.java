package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

/**
 * City repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ICityRepository extends IQueryDslBaseRepository<CityEntity> {

    /**
     * List all cities
     *
     * @return List cities
     */
    List<CityEntity> findAll();
}
