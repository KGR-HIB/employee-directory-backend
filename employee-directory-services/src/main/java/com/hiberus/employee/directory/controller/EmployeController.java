package com.hiberus.employee.directory.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
import com.hiberus.employee.directory.service.IEmployeService;
import com.hiberus.employee.directory.service.IUserService;
import com.hiberus.employee.directory.vo.Employe;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private IEmployeService employeService;

    @Lazy
    @Autowired
    @Getter
    private IUserService userService;

    /**
     * Crea empleado.
     * 
     * @author acachiguango on 01/07/2021
     * @param request EmployeeEntity
     * @return Response<Employe>
     */
    @PostMapping("/createOrUpdate")
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
     * Find the employees that contain as names, surnames or email, the query parameter
     *
     * @author bcueva
     * @param query Query to find
     * @return List of employees
     */
    @GetMapping("/{query}")
    @Operation(summary = "Find the employees that contain as names, surnames or email, the query parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of employees",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employe.class)))}
        )
    })
    public ResponseEntity<Response<List<Employe>>> findByNamesAndEmail(@NotBlank @PathVariable String query) {
        return new ResponseEntity<>(
            Response.<List<Employe>>builder().data(this.employeService.findByNamesAndEmail(query)).build(),
            HttpStatus.OK
        );
    }
}
