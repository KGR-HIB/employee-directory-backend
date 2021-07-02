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
     * Crea o actualiza empleado.
     * 
     * @author acachiguango on 01/07/2021
     * @param employeeEntity EmployeeEntity
     */
    void createOrUpdate(EmployeeEntity employeeEntity);

}
