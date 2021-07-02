package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.service.common.IBaseService;
import com.hiberus.employee.directory.vo.Certification;

/**
 * Certification service interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ICertificationService extends IBaseService<CertificationEntity> {

    /**
     * List all certifications
     *
     * @return List of CertificationEntity
     */
    List<Certification> findAll();
}
