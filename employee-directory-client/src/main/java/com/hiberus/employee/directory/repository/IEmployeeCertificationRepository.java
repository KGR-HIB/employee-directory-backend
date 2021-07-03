package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeCertificationEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Certification;

/**
 * Repository interface of EmployeeCertification
 *
 * @author bcueva
 * @version 1.0
 */
public interface IEmployeeCertificationRepository extends IQueryDslBaseRepository<EmployeeCertificationEntity> {

    /**
     * Find all certification has employee
     *
     * @author bcueva
     * @param employeeId Id of employee
     * @param status state
     * @return List of certifications
     */
    List<Certification> findByEmployeeId(Integer employeeId, Boolean status);

    /**
     * Create employee tag.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectIds project Ids
     * @param employeId employee Id
     * @param createdByUser user Id
     */
    void create(List<Integer> projectIds, Integer employeeId, Integer createdByUser);

    /**
     * Updates the status of the employee project.
     * 
     * @author acachiguango on 02/07/2021
     * @param projectIds project Ids
     * @param employeeId employe Id
     * @param createdByUser user Id
     * @param status state
     */
    void updateStatusEmployeeProject(List<Integer> projectIds, Integer employeeId, Integer createdByUser,
        Boolean status);
}
