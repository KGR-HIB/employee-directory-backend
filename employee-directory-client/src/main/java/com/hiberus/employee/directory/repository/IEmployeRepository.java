package com.hiberus.employee.directory.repository;

import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;

/**
 * IEmployeRepository.java
 * 
 * @author Kruger on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
public interface IEmployeRepository extends IQueryDslBaseRepository<EmployeeEntity> {
    /**
     * Crea empleado.
     * 
     * @author acachiguango on 01/07/2021
     * @param employeeEntity EmployeeEntity
     */
    void create(EmployeeEntity employeeEntity);

    /**
     * Actualiza empleado.
     * 
     * @author acachiguango on 01/07/2021
     * @param employeeEntity EmployeeEntity
     */
    void updateValues(EmployeeEntity employeeEntity);
}
