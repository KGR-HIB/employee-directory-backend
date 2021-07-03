package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.repository.ICertificationRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Certification;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Certification service implementation
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class CertificationService extends BaseService<CertificationEntity, ICertificationRepository>
    implements ICertificationService {

    public CertificationService(ICertificationRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Certification> findAll() {
        return repository.findAll();
    }
}
