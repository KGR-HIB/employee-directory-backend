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

    /**
     * Crea ciudad si no existe.
     * 
     * @author acachiguango on 01/07/2021
     * @param cityEntity CityEntity
     * @param createdByUser id
     * @return id ciudad
     */
    Integer createByName(CityEntity cityEntity, Integer createdByUser);

    /**
     * Crea ciudad.
     * 
     * @author acachiguango on 01/07/2021
     * @param cityEntity CityEntity
     */
    void create(CityEntity cityEntity);
}
