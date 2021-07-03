package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.City;

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
    List<City> findAll();

    /**
     * Create city if it does not exist.
     * 
     * @author acachiguango on 01/07/2021
     * @param cityEntity CityEntity
     * @param createdByUser id
     * @return id city
     */
    Integer createByName(CityEntity cityEntity, Integer createdByUser);

    /**
     * Create city.
     * 
     * @author acachiguango on 01/07/2021
     * @param cityEntity CityEntity
     */
    void create(CityEntity cityEntity);
}
