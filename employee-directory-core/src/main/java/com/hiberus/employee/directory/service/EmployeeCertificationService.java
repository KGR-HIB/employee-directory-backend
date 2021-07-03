package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.entity.EmployeeCertificationEntity;
import com.hiberus.employee.directory.repository.ICertificationRepository;
import com.hiberus.employee.directory.repository.IEmployeeCertificationRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Certification;

/**
 * EmployeeCertificationService.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 */
@Lazy
@Service
@Transactional
public class EmployeeCertificationService
    extends BaseService<EmployeeCertificationEntity, IEmployeeCertificationRepository>
    implements IEmployeeCertificationService {

    @Lazy
    @Autowired
    private ICertificationRepository certificationRepository;

    /**
     * Constructor.
     */
    public EmployeeCertificationService(IEmployeeCertificationRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Certification> createByName(List<CertificationEntity> certifications, Integer employeeId,
        Integer createdByUser) {
        List<Integer> certificationIds = this.certificationRepository.createByName(certifications, createdByUser);
        this.repository.create(certificationIds, employeeId, createdByUser);
        return this.repository.findByEmployeeId(employeeId, Boolean.TRUE);
    }

}
