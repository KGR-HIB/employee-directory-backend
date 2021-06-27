package com.hiberus.employee.directory.entity.id;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Id for Role - Functionality
 *
 * @author bcueva
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class RoleFunctionalityId implements Serializable {

    /**
     * Role functionality id
     */
    @Column(name = "ROLE_ID")
    private Integer roleId;

    /**
     * Role id
     */
    @Column(name = "FUNCTIONALITY_ID")
    private Integer functionalityId;
}
