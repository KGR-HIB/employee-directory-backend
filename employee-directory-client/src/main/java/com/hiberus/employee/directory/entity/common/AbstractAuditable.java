package com.hiberus.employee.directory.entity.common;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Abstract auditable
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@MappedSuperclass
public class AbstractAuditable implements Serializable {

    /**
     * True if the record is active otherwise false
     */
    @NotNull
    @Column(name = "STATUS", length = 1)
    protected Boolean status;

    /**
     * User who created the record
     */
    @NotNull
    @Column(name = "CREATED_BY_USER")
    protected Integer createdByUser;

    /**
     * Date the record was created
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    protected Date createDate;

    /**
     * User who made the last modification of the registry
     */
    @Column(name = "LAST_MODIFIED_BY_USER")
    protected Integer lastModifiedByUser;

    /**
     * Date of the last modification
     */
    @Column(name = "LAST_MODIFIED_DATE")
    protected Date lastModifiedDate;
}
