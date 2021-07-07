package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QCertificationEntity.certificationEntity;
import static com.querydsl.core.types.Projections.bean;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import com.hiberus.employee.directory.util.DateUtil;
import com.hiberus.employee.directory.util.NameUtil;
import com.hiberus.employee.directory.vo.Certification;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

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

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Integer> createByName(List<CertificationEntity> certifications, Integer createdByUser) {
        List<Integer> certificationIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(certifications)) {
            return certificationIds;
        }
        for (CertificationEntity certificationEntity : certifications) {
            if (null == certificationEntity.getId()) {
                certificationIds.add(this.validateByName(certificationEntity, createdByUser));
            } else {
                certificationIds.add(certificationEntity.getId());
            }
        }
        return certificationIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CertificationEntity certificationEntity) {
        certificationEntity.setName(NameUtil.clearAccents(certificationEntity.getName()));
        certificationEntity.setCreateDate(DateUtil.currentDate());
        certificationEntity.setStatus(Boolean.TRUE);
        this.save(certificationEntity);
    }

    /**
     * Validate by name.
     * 
     * @author acachiguango on 03/07/2021
     * @param certificationEntity CertificationEntity
     * @param createdByUser userId
     * @return certificationId
     */
    private Integer validateByName(CertificationEntity certificationEntity, Integer createdByUser) {
        CertificationEntity entity = this.findByName(certificationEntity.getName());
        if (null == entity) {
            certificationEntity.setCreatedByUser(createdByUser);
            this.create(certificationEntity);
            return certificationEntity.getId();
        }
        return entity.getId();
    }

    /**
     * Get tag by name.
     * 
     * @author acachiguango on 03/07/2021
     * @param name tag name
     * @return CertificationEntity
     */
    private CertificationEntity findByName(String name) {
        String value = NameUtil.clearAccents(name);
        BooleanBuilder where = new BooleanBuilder();
        where.and(certificationEntity.name.equalsIgnoreCase(value));
        where.and(certificationEntity.status.eq(Boolean.TRUE));
        JPQLQuery<CertificationEntity> query =
            from(certificationEntity).select(bean(CertificationEntity.class, certificationEntity.id));
        query.where(where);
        return query.fetchFirst();
    }
}
