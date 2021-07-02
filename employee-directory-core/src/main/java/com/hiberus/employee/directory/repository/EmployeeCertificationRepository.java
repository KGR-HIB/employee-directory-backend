package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCertificationEntity.certificationEntity;
import static com.hiberus.employee.directory.entity.QEmployeeCertificationEntity.employeeCertificationEntity;
import static com.querydsl.core.types.Projections.bean;

import java.util.List;
import com.hiberus.employee.directory.entity.EmployeeCertificationEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.Certification;
import com.querydsl.jpa.JPQLQuery;

/**
 * Repository implementation for EmployeeCertification
 *
 * @author bcueva
 * @version 1.0
 */
public class EmployeeCertificationRepository extends JPAQueryDslBaseRepository<EmployeeCertificationEntity> implements IEmployeeCertificationRepository {

    public EmployeeCertificationRepository() {
        super(EmployeeCertificationEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Certification> findByEmployeeId(Integer employeeId) {
        JPQLQuery<Certification> jpqlQuery = from(employeeCertificationEntity)
            .select(bean(Certification.class, certificationEntity.id, certificationEntity.name))
            .join(employeeCertificationEntity.certification, certificationEntity);

        jpqlQuery.where(
            certificationEntity.status.eq(Boolean.TRUE)
                .and(employeeCertificationEntity.status.eq(Boolean.TRUE))
                .and(employeeCertificationEntity.employeeId.eq(employeeId))
        );

        return jpqlQuery.fetch();
    }
}
