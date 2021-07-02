package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.ProjectResponseMapper;
import com.hiberus.employee.directory.service.IProjectService;
import com.hiberus.employee.directory.vo.Project;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Project
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/projects")
@Lazy
@Tag(name = "Project", description = "The Project API")
public class ProjectController {

    @Lazy
    @Autowired
    @Getter
    private IProjectService projectService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List all projects",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Project.class))) }
        )
    })
    public ResponseEntity<Response<List<Project>>> findAll() {
        List<Project> projectRespons = ProjectResponseMapper.MAPPER.toVOList(projectService.findAll());
        Response<List<Project>> response = Response.<List<Project>>builder()
            .data(projectRespons)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
