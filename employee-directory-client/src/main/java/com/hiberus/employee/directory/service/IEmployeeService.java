package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.exception.EmployeeDirectoryException;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.EmployeeFiltersRequest;
import org.springframework.data.domain.Page;

/**
 * IEmployeService.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
public interface IEmployeeService {

    /**
     * Create or update employee.
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

    /**
     * Get Sheet of employee
     *
     * @author bcueva
     * @param id Id of employee
     * @return Employee
     */
    Employe getSheetEmployee(Integer id) throws EmployeeDirectoryException;

    /**
     * Page the employees that match the filters
     *
     * @author bcueva
     * @param page Page number
     * @param size Number of elements per page
     * @param query Query by name, lastName or email
     * @param employeeFiltersRequest Advanced filters
     * @return Employee page
     */
    Page<Employe> pageByFilters(Integer page, Integer size, String query, EmployeeFiltersRequest employeeFiltersRequest);
}
