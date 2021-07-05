package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.exception.EmployeeDirectoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.repository.ICityRepository;
import com.hiberus.employee.directory.repository.IDepartmentRepository;
import com.hiberus.employee.directory.repository.IEmployeeCertificationRepository;
import com.hiberus.employee.directory.repository.IEmployeeProjectRepository;
import com.hiberus.employee.directory.repository.IEmployeeRepository;
import com.hiberus.employee.directory.repository.IEmployeeSkillRepository;
import com.hiberus.employee.directory.repository.IPositionRepository;
import com.hiberus.employee.directory.repository.IUserRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Certification;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.EmployeeFiltersRequest;
import com.hiberus.employee.directory.vo.Project;
import com.hiberus.employee.directory.vo.Skill;

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
public class EmployeeService extends BaseService<EmployeeEntity, IEmployeeRepository> implements IEmployeeService {

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
    public EmployeeService(IEmployeeRepository repository) {
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
    @Transactional(readOnly = true)
    @Override
    public List<Employe> findByNamesAndEmail(String query) {
        return this.repository.findByNamesAndEmail(query);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Employe getSheetEmployee(Integer id) throws EmployeeDirectoryException {
        // Get employee's principal information
        Employe employee = this.repository.findEmployeeMainInformationById(id);

        if (employee == null) {
            throw new EmployeeDirectoryException("No existe el empleado");
        }

        // Get projects assigned to the employee
        List<Project> projectList = this.employeeProjectRepository.findByEmployeeId(id, Boolean.TRUE);
        employee.setProjects(projectList);
        // Get certifications assigned to the employee
        List<Certification> certificationList = this.employeeCertificationRepository.findByEmployeeId(id, Boolean.TRUE);
        employee.setCertifications(certificationList);
        // Get Skills has employee
        List<Skill> skillList = this.employeeSkillRepository.findByEmployeeId(id, Boolean.TRUE);
        employee.setSkills(skillList);
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Page<Employe> pageByFilters(Integer page, Integer size, String query,
        EmployeeFiltersRequest employeeFiltersRequest) {
        Pageable pageable = PageRequest.of(page, size);
        return this.repository.pageByFilters(pageable, query, employeeFiltersRequest);
    }

}
