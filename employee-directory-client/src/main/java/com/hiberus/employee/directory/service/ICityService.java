package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.City;

/**
 * City service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ICityService extends IBaseService<CityEntity> {

    /**
     * Find all cities
     *
     * @return List of cities
     */
    List<City> findAll();
}
