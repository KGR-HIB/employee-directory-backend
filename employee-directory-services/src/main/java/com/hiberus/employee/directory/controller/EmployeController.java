package com.hiberus.employee.directory.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.entity.ProjectEntity;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
import com.hiberus.employee.directory.service.IEmployeeCertificationService;
import com.hiberus.employee.directory.service.IEmployeeProjectService;
import com.hiberus.employee.directory.service.IEmployeeService;
import com.hiberus.employee.directory.service.IUserService;
import com.hiberus.employee.directory.util.ProjectUtil;
import com.hiberus.employee.directory.vo.Certification;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.EmployeProjectRequest;
import com.hiberus.employee.directory.vo.EmployeeFiltersRequest;
import com.hiberus.employee.directory.vo.Project;
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

/**
 * EmployeController.
 * 
 * @author acachiguango on 01/07/2021
 * @version 1.0
 * @since 1.0.0
 */
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
            array = @ArraySchema(schema = @Schema(implementation = Employe.class))) }) })
    public ResponseEntity<Response<Employe>> createOrUpdate(@RequestBody EmployeeEntity request) {
        if (null == request.getUser().getId() && this.userService.existsByMail(request.getUser().getEmail())) {
            return ResponseEntity.ok()
                .body(Response.<Employe>builder().code(1).message("User already exists.").build());
        }
        request.setCreatedByUser(AuthSecurityUtil.getUserLogin().getId());
        this.employeService.createOrUpdate(request);
        return ResponseEntity.ok().body(Response.<Employe>builder().data(Employe.builder().id(request.getId()).build())
            .code(200).message("success").build());
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
            array = @ArraySchema(schema = @Schema(implementation = Employe.class))) }) })
    public ResponseEntity<Response<List<Employe>>> findByNamesAndEmail(@NotBlank @RequestParam String query) {
        return new ResponseEntity<>(
            Response.<List<Employe>>builder().data(this.employeService.findByNamesAndEmail(query)).build(),
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
            .data(this.employeeProjectService.createByName(projects, request.getEmployeeId(), createdByUser)).code(200)
            .message("success").build(), HttpStatus.OK);
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
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class)) }) })
    public ResponseEntity<Response<Employe>> getSheetEmployee(@NotBlank @PathVariable Integer id) {
        return new ResponseEntity<>(
            Response.<Employe>builder().data(this.employeService.getSheetEmployee(id)).code(200).build(),
            HttpStatus.OK);
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
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "add certificate to employee",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Employe.class))) }) })
    public ResponseEntity<Response<List<Certification>>>
        addCertifications(@Valid @RequestBody EmployeeCertificationRequest request) {
        Integer createdByUser = AuthSecurityUtil.getUserLogin().getId();
        List<CertificationEntity> certifications = ProjectUtil.getCertificationEntities(request.getCertifications());
        return new ResponseEntity<>(Response.<List<Certification>>builder()
            .data(
                this.employeeCertificationService.createByName(certifications, request.getEmployeeId(), createdByUser))
            .message("success").build(), HttpStatus.OK);
    }

    /**
     * Get page of employee
     *
     * @param page Page number
     * @param size Number of elements per page
     * @param query Query by name, lastName or email
     * @param employeeFiltersRequest Advanced filters
     * @return Employee page
     */
    @PostMapping("/page")
    @Operation(summary = "Employees page that match the filters entered")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee page",
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employe.class)) }) })
    public ResponseEntity<Response<PageResponse<Employe>>> page(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String query, @RequestBody
        EmployeeFiltersRequest employeeFiltersRequest) {
        Page<Employe> pageEmployee = this.employeService.pageByFilters(page, size, query, employeeFiltersRequest);
        PageResponse<Employe> pageResponse = PageResponse.<Employe>builder()
            .data(pageEmployee.getContent())
            .total(pageEmployee.getTotalElements())
            .totalPages(pageEmployee.getTotalPages())
            .currentPage(pageEmployee.getNumber())
            .build();
        return new ResponseEntity<>(Response.<PageResponse<Employe>>builder()
            .data(pageResponse)
            .build(), HttpStatus.OK);
    }

}
