package com.hiberus.employee.directory.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.vo.Project;
import lombok.extern.slf4j.Slf4j;

/**
 * ProjectUtil.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
@Slf4j
public final class ProjectUtil {
    /**
     * Constructor.
     */
    private ProjectUtil() {
    }

    /**
     * Transform to entity.
     * 
     * @author acachiguango on 02/07/2021
     * @param projects List Project
     * @return List ProjectEntity
     */
    public static List<ProjectEntity> getEntities(List<Project> projects) {
        List<ProjectEntity> entities = new ArrayList<>();
        if (CollectionUtils.isEmpty(projects)) {
            return entities;
        }
        for (Project project : projects) {
            ProjectEntity projectEntity = new ProjectEntity();
            try {
                PropertyUtils.copyProperties(projectEntity, project);
                entities.add(projectEntity);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error(e.getMessage());
            }
        }
        return entities;
    }
}
