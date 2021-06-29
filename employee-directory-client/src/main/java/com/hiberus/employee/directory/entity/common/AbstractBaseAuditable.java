package com.hiberus.employee.directory.entity.common;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Abstract auditable
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AbstractBaseAuditable implements Serializable {

    /**
     * True if the record is active otherwise false
     */
    @Column(name = "STATUS", columnDefinition = "boolean default true")
    protected Boolean status;

    /**
     * User who created the record
     */
    @CreatedBy
    @NotNull
    @Column(name = "CREATED_BY_USER", updatable = false)
    protected Integer createdByUser;

    /**
     * Date the record was created
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    protected Date createDate;

    /**
     * User who made the last modification of the registry
     */
    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY_USER")
    protected Integer lastModifiedByUser;

    /**
     * Date of the last modification
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE")
    protected Date lastModifiedDate;
}
