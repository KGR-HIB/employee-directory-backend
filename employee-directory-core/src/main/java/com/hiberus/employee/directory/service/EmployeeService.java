package com.hiberus.employee.directory.service;

import java.util.List;
import javax.transaction.Transactional;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.ICityRepository;
import com.hiberus.employee.directory.repository.IDepartmentRepository;
import com.hiberus.employee.directory.repository.IEmployeRepository;
import com.hiberus.employee.directory.repository.IEmployeeCertificationRepository;
import com.hiberus.employee.directory.repository.IEmployeeProjectRepository;
import com.hiberus.employee.directory.repository.IEmployeeSkillRepository;
import com.hiberus.employee.directory.repository.IPositionRepository;
import com.hiberus.employee.directory.repository.IUserRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Certification;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.Project;
import com.hiberus.employee.directory.vo.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * EmployeService.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Lazy
@Service
@Transactional
public class EmployeService extends BaseService<EmployeeEntity, IEmployeRepository> implements IEmployeService {

    @Lazy
    @Autowired
    private ICityRepository cityRepository;

    @Lazy
    @Autowired
    private IPositionRepository positionRepository;

    @Lazy
    @Autowired
    private IDepartmentRepository deptRepository;

    @Lazy
    @Autowired
    private IUserRepository userRepository;

    @Lazy
    @Autowired
    private IEmployeeProjectRepository employeeProjectRepository;

    @Lazy
    @Autowired
    private IEmployeeCertificationRepository employeeCertificationRepository;

    @Lazy
    @Autowired
    private IEmployeeSkillRepository employeeSkillRepository;

    /**
     * Constructor.
     * 
     * @param repository IEmployeRepository
     */
    public EmployeService(IEmployeRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOrUpdate(EmployeeEntity employeeEntity) {
        Integer createdByUser = employeeEntity.getCreatedByUser();
        employeeEntity.setCityId(this.cityRepository.createByName(employeeEntity.getCity(), createdByUser));
        employeeEntity.setPositionId(this.positionRepository.createByName(employeeEntity.getPosition(), createdByUser));
        employeeEntity.setDepartmentId(this.deptRepository.createByName(employeeEntity.getDepartment(), createdByUser));
        employeeEntity.setUserId(this.userRepository.createOrUpdate(employeeEntity.getUser(), createdByUser));
        if (null == employeeEntity.getId()) {
            this.repository.create(employeeEntity);
        } else {
            employeeEntity.setLastModifiedByUser(createdByUser);
            this.repository.updateValues(employeeEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employe> findByNamesAndEmail(String query) {
        return this.repository.findByNamesAndEmail(query);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employe getSheetEmployee(Integer id) {
        // Get employee's principal information
        Employe employee = this.repository.findEmployeeMainInformationById(id);
        // Get projects assigned to the employee
        List<Project> projectList = this.employeeProjectRepository.findByEmployeeId(id);
        employee.setProjects(projectList);
        // Get certifications assigned to the employee
        List<Certification> certificationList = this.employeeCertificationRepository.findByEmployeeId(id);
        employee.setCertifications(certificationList);
        // Get Skills has employee
        List<Skill> skillList = this.employeeSkillRepository.findByEmployeeId(id);
        employee.setSkills(skillList);
        return employee;
    }

}
