package com.hiberus.employee.directory.repository;

import java.util.List;
import com.hiberus.employee.directory.entity.RoleFunctionalityEntity;
import com.hiberus.employee.directory.repository.common.IQueryDslBaseRepository;
import com.hiberus.employee.directory.vo.RoleFunctionality;

/**
 * IRoleFunctionalityRepository.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
public interface IRoleFunctionalityRepository extends IQueryDslBaseRepository<RoleFunctionalityEntity> {

    /**
     * Get functionalities by roleId.
     * 
     * @author acachiguango on 02/07/2021
     * @param roleId id role
     * @return List RoleFunctionality
     */
    List<RoleFunctionality> findByRoleId(Integer roleId);

}
