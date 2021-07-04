package com.hiberus.employee.directory.vo;

import java.util.List;
import com.hiberus.employee.directory.vo.validate.NotBlankConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeeSkillRequest.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSkillRequest {
    @NotBlankConstraint
    private Integer employeeId;
    private List<Skill> skills;
}
