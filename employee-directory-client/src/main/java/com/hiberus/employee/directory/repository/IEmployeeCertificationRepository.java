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
     * @return List of certifications
     */
    List<Certification> findByEmployeeId(Integer employeeId);
}
