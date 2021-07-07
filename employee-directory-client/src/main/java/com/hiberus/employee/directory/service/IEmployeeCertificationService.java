package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.entity.EmployeeCertificationEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Certification;

/**
 * IEmployeeCertificationService.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 */
public interface IEmployeeCertificationService extends IBaseService<EmployeeCertificationEntity> {
    /**
     * Create tag if it does not exist.
     * 
     * @author acachiguango on 03/07/2021
     * @param certifications List CertificationEntity
     * @param employeeId employee Id
     * @param createdByUser user Id
     * @return List Certification
     */
    List<Certification> createByName(List<CertificationEntity> certifications, Integer employeeId,
        Integer createdByUser);

}
