package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.vo.Employe;

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

    /**
     * Find employees by name, lastName and email
     *
     * @author bcueva
     * @param query Param to query
     * @return List of employees
     */
    List<Employe> findByNamesAndEmail(String query);
}
