package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCertificationEntity.certificationEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Certification;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Certification repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class CertificationRepository extends JPAQueryDslBaseRepository<CertificationEntity>
    implements ICertificationRepository {

    public CertificationRepository() {
        super(CertificationEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Certification> findAll() {
        return from(certificationEntity)
            .select(bean(Certification.class, certificationEntity.id, certificationEntity.name))
            .where(certificationEntity.status.eq(Boolean.TRUE)).fetch();
    }
}
