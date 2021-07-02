package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.vo.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapping from ProjectEntity to ProjectResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface ProjectResponseMapper {

    ProjectResponseMapper MAPPER = Mappers.getMapper(ProjectResponseMapper.class);

    /**
     * Mapping ProjectEntity to ProjectResponse
     *
     * @param projectEntity Project entity instance
     * @return Project response instance
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    Project toVO(ProjectEntity projectEntity);

    /**
     * Mapping ProjectEntity list to ProjectResponse list
     * @param projectEntityList
     * @return
     */
    List<Project> toVOList(List<ProjectEntity> projectEntityList);
}
