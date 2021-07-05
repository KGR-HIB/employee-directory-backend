package com.hiberus.employee.directory.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employe.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employe {
    private Integer id;
    private String name;
    private String lastName;
    private String phone;
    private String photoPath;
    private String photo;
    private City city;
    private Department department;
    private Position position;
    private Employe immediateChief;
    private User user;
    private List<Project> projects;
    private List<Certification> certifications;
    private List<Skill> skills;
}
