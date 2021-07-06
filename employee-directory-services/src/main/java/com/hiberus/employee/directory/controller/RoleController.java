package com.hiberus.employee.directory.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.common.Constants;
import com.hiberus.employee.directory.service.IRoleService;
import com.hiberus.employee.directory.vo.Role;
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
 * RoleController.
 * 
 * @author acachiguango on 06/07/2021
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/role")
@Lazy
@Tag(name = "Role", description = "Role API")
public class RoleController {

    @Lazy
    @Autowired
    @Getter
    private IRoleService roleService;

    /**
     * Get all roles.
     * 
     * @author acachiguango on 06/07/2021
     * @return List Role
     */
    @GetMapping("/findAll")
    @Operation(summary = "Get all roles")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List of roles",
        content = { @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Role.class))) }) })
    public ResponseEntity<Response<List<Role>>> findAll() {
        return ResponseEntity.ok().body(Response.<List<Role>>builder().data(this.roleService.findAll())
            .code(Constants.OK).message(Constants.SUCCESS).build());
    }
}
