package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.vo.SkillResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapping SkillEntity to SkillResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface SkillResponseMapper {

    SkillResponseMapper MAPPER = Mappers.getMapper(SkillResponseMapper.class);

    /**
     * Mapping SkillEntity to SkillResponse
     *
     * @param skillEntity SkillEntity instance
     * @return SkillResponse instance
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    SkillResponse toVO(SkillEntity skillEntity);

    /**
     * Mapping SkillEntity list to SkillResponse list
     *
     * @param skillEntityList SkillEntity list
     * @return SkillResponse list
     */
    List<SkillResponse> toVOList(List<SkillEntity> skillEntityList);
}
