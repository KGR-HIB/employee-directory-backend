package com.hiberus.employee.directory.service;

import com.hiberus.employee.directory.entity.EmployeeEntity;

/**
 * IEmployeService.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
public interface IEmployeService {

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
    void update(EmployeeEntity employeeEntity);

}
