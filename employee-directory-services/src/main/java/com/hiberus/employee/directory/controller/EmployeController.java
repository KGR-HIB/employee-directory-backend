package com.hiberus.employee.directory.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.employee.directory.common.Constants;
import com.hiberus.employee.directory.entity.CertificationEntity;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.entity.SkillEntity;
import com.hiberus.employee.directory.exception.EmployeeDirectoryException;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
import com.hiberus.employee.directory.service.IEmployeeCertificationService;
import com.hiberus.employee.directory.service.IEmployeeProjectService;
import com.hiberus.employee.directory.service.IEmployeeService;
import com.hiberus.employee.directory.service.IEmployeeSkillService;
import com.hiberus.employee.directory.service.IUserService;
import com.hiberus.employee.directory.util.FileUtil;
import com.hiberus.employee.directory.util.ProjectUtil;
import com.hiberus.employee.directory.vo.Certification;
import com.hiberus.employee.directory.vo.Employee;
import com.hiberus.employee.directory.vo.EmployeProjectRequest;
import com.hiberus.employee.directory.vo.EmployeeCertificationRequest;
import com.hiberus.employee.directory.vo.EmployeeFiltersRequest;
import com.hiberus.employee.directory.vo.EmployeeSkillRequest;
import com.hiberus.employee.directory.vo.Project;
import com.hiberus.employee.directory.vo.Skill;
import com.hiberus.employee.directory.vo.common.PageResponse;
import com.hiberus.employee.directory.vo.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * EmployeController.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
@Lazy
@Tag(name = "Employe", description = "Employe API")
public class EmployeController {

    @Lazy
    @Autowired
    @Getter
    private IEmployeeService employeService;

    @Lazy
    @Autowired
    @Getter
    private IUserService userService;

    @Lazy
    @Autowired
    @Getter
    private IEmployeeProjectService employeeProjectService;

    @Lazy
    @Autowired
    @Getter
    private IEmployeeCertificationService employeeCertificationService;

    @Lazy
    @Autowired
    @Getter
    private IEmployeeSkillService employeeSkillService;

    @Lazy
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Create employee.
     * 
     * @author acachiguango on 01/07/2021
     * @param request EmployeeEntity
     * @return Response<Employe>
     */
    @PostMapping("/createOrUpdate")
    @Operation(summary = "Create or update employees.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create or update employees",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Employee.class))) }) })
    public ResponseEntity<Response<Employee>> createOrUpdate(
        @RequestParam(value = "file", required = true) MultipartFile file,
        @RequestParam(value = "data", required = true) String data) {
        EmployeeEntity request = null;
        try {
            request = this.objectMapper.readValue(data, EmployeeEntity.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        if (null == request.getId() && this.userService.existsByMail(request.getUser().getEmail())) {
            return ResponseEntity.internalServerError()
                .body(Response.<Employee>builder().code(Constants.ERROR).message("User already exists.").build());
        }
        request.setCreatedByUser(AuthSecurityUtil.getUserLogin().getId());
        this.employeService.createOrUpdate(request);
        try {
            FileUtil.saveFile(file.getBytes(), request.getId());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().body(Response.<Employee>builder().data(Employee.builder().id(request.getId()).build())
            .code(Constants.OK).message(Constants.SUCCESS).build());
    }

    /**
     * Find the employees that contain as names, surnames or email, the query
     * parameter
     *
     * @author bcueva
     * @param query Query to find
     * @return List of employees
     */
    @GetMapping
    @Operation(summary = "Find the employees that contain as names, surnames or email, the query parameter")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List of employees",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Employee.class))) }) })
    public ResponseEntity<Response<List<Employee>>> findByNamesAndEmail(@NotBlank @RequestParam String query) {
        return new ResponseEntity<>(Response.<List<Employee>>builder()
            .data(this.employeService.findByNamesAndEmail(query)).code(Constants.OK).message(Constants.SUCCESS).build(),
            HttpStatus.OK);
    }

    /**
     * Add project to employee.
     * 
     * @author acachiguango on 02/07/2021
     * @param request EmployeProjectRequest
     * @return List of projects
     */
    @PostMapping("/projects/add")
    @Operation(summary = "Add project")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Add project to employee",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Project.class))) }) })
    public ResponseEntity<Response<List<Project>>> addProjects(@Valid @RequestBody EmployeProjectRequest request) {
        Integer createdByUser = AuthSecurityUtil.getUserLogin().getId();
        List<ProjectEntity> projects = ProjectUtil.getProjectEntities(request.getProjects());
        return new ResponseEntity<>(Response.<List<Project>>builder()
            .data(this.employeeProjectService.createByName(projects, request.getEmployeeId(), createdByUser))
            .code(Constants.OK).message(Constants.SUCCESS).build(), HttpStatus.OK);
    }

    /**
     * Get sheet of employee
     *
     * @author bcueva
     * @param id Id of Employee
     * @return Employee's sheet
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get information of Employee's sheet")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee's sheet",
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }) })
    public ResponseEntity<Response<Employee>> getSheetEmployee(@NotBlank @PathVariable Integer id) {
        try {
            Employee employee = this.employeService.getSheetEmployee(id);
            return new ResponseEntity<>(
                Response.<Employee>builder().data(employee).code(Constants.OK).message(Constants.SUCCESS).build(),
                HttpStatus.OK);
        } catch (EmployeeDirectoryException e) {
            return new ResponseEntity<>(
                Response.<Employee>builder().message(e.getMessage()).code(HttpStatus.NOT_FOUND.value()).build(),
                HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add certificate to employee.
     * 
     * @author acachiguango on 02/07/2021
     * @param request EmployeProjectRequest
     * @return certification List
     */
    @PostMapping("/certifications/add")
    @Operation(summary = "Add certificate to employee")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Add certificate to employee",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Certification.class))) }) })
    public ResponseEntity<Response<List<Certification>>>
        addCertifications(@Valid @RequestBody EmployeeCertificationRequest request) {
        Integer createdByUser = AuthSecurityUtil.getUserLogin().getId();
        List<CertificationEntity> certifications = ProjectUtil.getCertificationEntities(request.getCertifications());
        return new ResponseEntity<>(Response.<List<Certification>>builder()
            .data(
                this.employeeCertificationService.createByName(certifications, request.getEmployeeId(), createdByUser))
            .code(Constants.OK).message(Constants.SUCCESS).build(), HttpStatus.OK);
    }

    /**
     * Get page of employee
     *
     * @author bcueva
     * @param page Page number
     * @param size Number of elements per page
     * @param query Query by name, lastName or email
     * @param employeeFiltersRequest Advanced filters
     * @return Employee page
     */
    @PostMapping("/page")
    @Operation(summary = "Employees page that match the filters entered")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee page",
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }) })
    public ResponseEntity<Response<PageResponse<Employee>>> page(@RequestParam Integer page, @RequestParam Integer size,
        @RequestParam String query, @RequestBody EmployeeFiltersRequest employeeFiltersRequest) {
        Page<Employee> pageEmployee = this.employeService.pageByFilters(page, size, query, employeeFiltersRequest);
        PageResponse<Employee> pageResponse =
            PageResponse.<Employee>builder().data(pageEmployee.getContent()).total(pageEmployee.getTotalElements())
                .totalPages(pageEmployee.getTotalPages()).currentPage(pageEmployee.getNumber()).build();
        return new ResponseEntity<>(Response.<PageResponse<Employee>>builder().data(pageResponse).code(Constants.OK)
            .message(Constants.SUCCESS).build(), HttpStatus.OK);
    }

    /**
     * Add skill to employee.
     * 
     * @author acachiguango on 02/07/2021
     * @param request EmployeProjectRequest
     * @return certification List
     */
    @PostMapping("/skills/add")
    @Operation(summary = "Add skill to employee")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Add skill to employee",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Skill.class))) }) })
    public ResponseEntity<Response<List<Skill>>> addSkills(@Valid @RequestBody EmployeeSkillRequest request) {
        Integer createdByUser = AuthSecurityUtil.getUserLogin().getId();
        List<SkillEntity> skills = ProjectUtil.getSkillEntities(request.getSkills());
        return new ResponseEntity<>(Response.<List<Skill>>builder()
            .data(this.employeeSkillService.createByName(skills, request.getEmployeeId(), createdByUser))
            .code(Constants.OK).message(Constants.SUCCESS).build(), HttpStatus.OK);
    }

    /**
     * Get employee photo.
     * 
     * @author acachiguango on 05/07/2021
     * @param employeeId employee Id
     * @return photo base64
     */
    @GetMapping("/photo/{employeeId}")
    @Operation(summary = "Get employee photo")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get employee photo",
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }) })
    public ResponseEntity<Response<String>> getPhoto(@NotNull @PathVariable Integer employeeId) {
        String photo64 = FileUtil.getBase64(employeeId);
        return new ResponseEntity<>(
            Response.<String>builder().data(photo64).code(Constants.OK).message(Constants.SUCCESS).build(),
            HttpStatus.OK);
    }

}
