package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Employe;

/**
 * IEmployeRepository.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 */
public interface IEmployeeRepository extends IQueryDslBaseRepository<EmployeeEntity> {
    /**
     * Create employee.
     * 
     * @author acachiguango on 01/07/2021
     * @param employeeEntity EmployeeEntity
     */
    void create(EmployeeEntity employeeEntity);

    /**
     * Udate employee.
     * 
     * @author acachiguango on 01/07/2021
     * @param employeeEntity EmployeeEntity
     */
    void updateValues(EmployeeEntity employeeEntity);

    /**
     * Find employees by name, lastName and email
     *
     * @author bcueva
     * @param query Param to query
     * @return List of employees
     */
    List<Employe> findByNamesAndEmail(String query);

    /**
     * Find employee main information by id
     *
     * @author bcueva
     * @param id Id of employee
     * @return Employee instance
     */
    Employe findEmployeeMainInformationById(Integer id);
}
