package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.DepartmentEntity;
import com.hiberus.employee.directory.vo.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper from DepartmentEntity to DepartmentResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface DepartmentResponseMapper {

    DepartmentResponseMapper MAPPER = Mappers.getMapper(DepartmentResponseMapper.class);

    /**
     * Mapping DepartmentEntity to DepartmentResponse
     *
     * @param departmentEntity DepartmentEntity instance
     * @return DepartmentResponse instance
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    DepartmentResponse toVO(DepartmentEntity departmentEntity);

    /**
     * Mapping DepartmentEntity list to DepartmentResponse entity
     *
     * @param departmentEntityList List of Departments entity
     * @return List of Departments response
     */
    List<DepartmentResponse> toVOList(List<DepartmentEntity> departmentEntityList);
}
