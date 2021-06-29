package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

/**
 * Position repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IPositionRepository extends IQueryDslBaseRepository<PositionEntity>  {

    /**
     * Find all departments
     *
     * @return List of departments
     */
    List<PositionEntity> findAll();
}
