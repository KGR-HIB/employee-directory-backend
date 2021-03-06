package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Position;

/**
 * Position service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface IPositionService extends IBaseService<PositionEntity> {

    /**
     * Find all departments
     *
     * @return List of departments
     */
    List<Position> findAll();
}
