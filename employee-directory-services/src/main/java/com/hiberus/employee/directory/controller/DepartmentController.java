package com.hiberus.employee.directory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.common.Constants;
import com.hiberus.employee.directory.service.IDepartmentService;
import com.hiberus.employee.directory.vo.Department;
import com.hiberus.employee.directory.vo.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

/**
 * Rest controller for department
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/departments")
@Lazy
@Tag(name = "Department", description = "The Department API")
public class DepartmentController {

    @Lazy
    @Autowired
    @Getter
    private IDepartmentService departmentService;

    /**
     * Find all departments
     *
     * @return List of Departments
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all departments")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List all departments",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Department.class))) }) })
    public ResponseEntity<Response<List<Department>>> findAll() {
        return new ResponseEntity<>(Response.<List<Department>>builder().data(departmentService.findAll())
            .code(Constants.OK).message(Constants.SUCCESS).build(), HttpStatus.OK);
    }
}
