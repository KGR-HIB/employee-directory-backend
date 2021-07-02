package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Certification;

/**
 * Certification repository interface
 *
 * @author bcueva
 * @version 1.0
 */
public interface ICertificationRepository extends IQueryDslBaseRepository<CertificationEntity> {

    /**
     * List all certifications
     *
     * @return List of CertificationEntity
     */
    List<Certification> findAll();
}
