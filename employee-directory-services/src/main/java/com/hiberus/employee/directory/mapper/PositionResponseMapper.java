package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.vo.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper from PositionEntity to PositionResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface PositionResponseMapper {

    PositionResponseMapper MAPPER = Mappers.getMapper(PositionResponseMapper.class);

    /**
     * Mapper PositionEntity to PositionResponse
     *
     * @param positionEntity PositionEntity instance
     * @return PositionResponse instance
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    PositionResponse toVO(PositionEntity positionEntity);

    /**
     * Mapper PositionEntity list to PositionResponse list
     *
     * @param positionEntityList PositionEntity list
     * @return PositionResponse list
     */
    List<PositionResponse> toVOList(List<PositionEntity> positionEntityList);
}
