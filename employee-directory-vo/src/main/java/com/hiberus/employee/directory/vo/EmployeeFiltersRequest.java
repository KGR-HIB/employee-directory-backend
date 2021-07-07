package com.hiberus.employee.directory.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee filters request vo
 *
 * @author bcueva
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFiltersRequest {
    List<Integer> positions;
    List<Integer> departments;
    List<Integer> projects;
    List<Integer> cities;
    List<Integer> skills;
    List<Integer> certifications;
}
