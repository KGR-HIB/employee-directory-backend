package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.vo.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper from CityEntity to CityResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface CityResponseMapper {

    CityResponseMapper MAPPER = Mappers.getMapper(CityResponseMapper.class);

    /**
     * Mapping CityEntity to CityResponse
     *
     * @param cityEntity City entity
     * @return City response
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    City toVO(CityEntity cityEntity);

    /**
     * Mapping CityEntity list to CityResponse list
     *
     * @param cityEntityList City entity list
     * @return City response list
     */
    List<City> toVOList(List<CityEntity> cityEntityList);
}
