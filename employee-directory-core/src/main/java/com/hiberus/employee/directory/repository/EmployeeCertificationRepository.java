package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCertificationEntity.certificationEntity;
import static com.hiberus.employee.directory.entity.QEmployeeCertificationEntity.employeeCertificationEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.EmployeeCertificationEntity;
import com.hiberus.employee.directory.entity.QEmployeeCertificationEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.vo.Certification;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

/**
 * Repository implementation for EmployeeCertification
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class EmployeeCertificationRepository extends JPAQueryDslBaseRepository<EmployeeCertificationEntity>
    implements IEmployeeCertificationRepository {

    public EmployeeCertificationRepository() {
        super(EmployeeCertificationEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Certification> findByEmployeeId(Integer employeeId, Boolean status) {
        JPQLQuery<Certification> query = from(employeeCertificationEntity).select(bean(Certification.class,
            certificationEntity.id, certificationEntity.name, employeeCertificationEntity.status));
        query.join(employeeCertificationEntity.certification, certificationEntity);
        BooleanBuilder where = new BooleanBuilder();
        where.and(employeeCertificationEntity.employeeId.eq(employeeId));
        where.and(certificationEntity.status.eq(Boolean.TRUE));
        if (null != status) {
            where.and(employeeCertificationEntity.status.eq(status));
        }
        query.where(where);
        return query.fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(List<Integer> certificationIds, Integer employeeId, Integer createdByUser) {
        if (CollectionUtils.isEmpty(certificationIds)) {
            this.updateStatusEmployeeCertification(certificationIds, employeeId, createdByUser, Boolean.FALSE);
            return;
        }
        List<Certification> certifications = this.findByEmployeeId(employeeId, null);
        List<Integer> create = new ArrayList<>();
        List<Integer> updateActive = new ArrayList<>();
        for (Integer projectId : certificationIds) {
            Certification cerification =
                certifications.stream().filter(cer -> cer.getId().equals(projectId)).findFirst().orElse(null);
            if (null == cerification) {
                create.add(projectId);
            } else if (!cerification.getStatus()) {
                updateActive.add(cerification.getId());
            }
        }
        // Create tag relationship
        if (!CollectionUtils.isEmpty(create)) {
            for (Integer certificationId : create) {
                EmployeeCertificationEntity entity = new EmployeeCertificationEntity();
                entity.setCreateDate(DateUtil.currentDate());
                entity.setCreatedByUser(createdByUser);
                entity.setEmployeeId(employeeId);
                entity.setCertificationId(certificationId);
                entity.setStatus(Boolean.TRUE);
                this.save(entity);
            }
        }
        // Update active tag status
        if (!CollectionUtils.isEmpty(updateActive)) {
            this.updateStatusEmployeeCertification(updateActive, employeeId, createdByUser, Boolean.TRUE);
        }
        // Update inactive tag status
        List<Integer> updateInactive = certifications.stream().filter(pro -> !certificationIds.contains(pro.getId()))
            .map(Certification::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(updateInactive)) {
            this.updateStatusEmployeeCertification(updateInactive, employeeId, createdByUser, Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatusEmployeeCertification(List<Integer> certificationIds, Integer employeeId,
        Integer createdByUser, Boolean status) {
        QEmployeeCertificationEntity qEmployeeCertificationEntity =
            QEmployeeCertificationEntity.employeeCertificationEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEmployeeCertificationEntity.employeeId.eq(employeeId));
        if (!CollectionUtils.isEmpty(certificationIds)) {
            where.and(qEmployeeCertificationEntity.certificationId.in(certificationIds));
        }
        update(qEmployeeCertificationEntity).where(where).set(qEmployeeCertificationEntity.status, status)
            .set(qEmployeeCertificationEntity.lastModifiedByUser, createdByUser)
            .set(qEmployeeCertificationEntity.lastModifiedDate, DateUtil.currentDate()).execute();
    }

}
