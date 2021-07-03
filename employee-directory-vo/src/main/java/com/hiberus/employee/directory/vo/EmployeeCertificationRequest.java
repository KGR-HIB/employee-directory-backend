package com.hiberus.employee.directory.vo;

import java.util.List;
import com.hiberus.employee.directory.vo.validate.NotBlankConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeeCertificationRequest.
 * 
 * @author acachiguango on 03/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCertificationRequest {
    @NotBlankConstraint
    private Integer employeeId;
    private List<Certification> certifications;
}
