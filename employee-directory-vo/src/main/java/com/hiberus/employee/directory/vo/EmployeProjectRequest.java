package com.hiberus.employee.directory.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeProjectRequest.
 * 
 * @author acachiguango on 02/07/2021
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeProjectRequest {
    private Integer employeId;
    private List<Project> projects;
}
