package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Position;

/**
 * Position repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IPositionRepository extends IQueryDslBaseRepository<PositionEntity> {

    /**
     * Find all departments
     *
     * @return List of departments
     */
    List<Position> findAll();

    /**
     * Create position if it doesn't exist.
     * 
     * @author acachiguango on 01/07/2021
     * @param positionEntity PositionEntity
     * @param createdByUser id
     * @return id ciudad
     */
    Integer createByName(PositionEntity positionEntity, Integer createdByUser);

    /**
     * Create position.
     * 
     * @author acachiguango on 01/07/2021
     * @param positionEntity PositionEntity
     */
    void create(PositionEntity positionEntity);
}
