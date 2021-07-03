package com.hiberus.employee.directory.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.vo.Certification;
import com.hiberus.employee.directory.vo.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProjectUtil.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
@Slf4j
@Builder
@Getter
public final class ProjectUtil {

    /**
     * Transform to entity.
     * 
     * @author acachiguango on 03/07/2021
     * @param source object
     * @param targetType Class type
     * @return entity
     */
    private static <T> T convert(Object source, Class<T> targetType) {
        T entity = null;
        try {
            entity = targetType.newInstance();
            PropertyUtils.copyProperties(entity, source);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
            | InstantiationException e) {
            log.error(e.getMessage());
        }
        return entity;
    }

    /**
     * Transform to entities.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List Project
     * @return List ProjectEntity
     */
    public static List<ProjectEntity> getProjectEntities(List<Project> values) {
        List<ProjectEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(values)) {
            return entities;
        }
        for (Object value : values) {
            entities.add(convert(value, ProjectEntity.class));
        }
        return entities;
    }

    /**
     * Transform to entities.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List Project
     * @return List ProjectEntity
     */
    public static List<CertificationEntity> getCertificationEntities(List<Certification> values) {
        List<CertificationEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(values)) {
            return entities;
        }
        for (Object value : values) {
            entities.add(convert(value, CertificationEntity.class));
        }
        return entities;
    }

}
