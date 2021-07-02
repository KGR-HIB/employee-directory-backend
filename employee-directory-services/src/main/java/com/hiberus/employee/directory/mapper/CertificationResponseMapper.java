package com.hiberus.employee.directory.mapper;

import java.util.List;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.vo.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapping from CertificationEntity to CertificationResponse
 *
 * @author bcueva
 * @version 1.0
 */
@Mapper
public interface CertificationResponseMapper {

    CertificationResponseMapper MAPPER = Mappers.getMapper(CertificationResponseMapper.class);

    /**
     * Mapping CertificationEntity to CertificationResponse
     *
     * @param certificationEntity CertificationEntity instance
     * @return CertificationResponse instance
     */
    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "name", target = "name")
    })
    Certification toVO(CertificationEntity certificationEntity);

    /**
     * Mapping CertificationEntity list to CertificationResponse list
     *
     * @param certificationEntityList CertificationEntity list
     * @return CertificationResponse list
     */
    List<Certification> toVOList(List<CertificationEntity> certificationEntityList);
}
