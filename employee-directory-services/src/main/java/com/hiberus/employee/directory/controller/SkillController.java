package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.SkillResponseMapper;
import com.hiberus.employee.directory.service.ISkillService;
import com.hiberus.employee.directory.vo.SkillResponse;
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
 * Rest controller for Skill
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/skills")
@Lazy
@Tag(name = "Skill", description = "The Skill API")
public class SkillController {

    @Lazy
    @Autowired
    @Getter
    private ISkillService skillService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List all skills",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SkillResponse.class))) }
        )
    })
    public ResponseEntity<Response<List<SkillResponse>>> findAll() {
        List<SkillResponse> skillResponses = SkillResponseMapper.MAPPER.toVOList(skillService.findAll());
        Response<List<SkillResponse>> response = Response.<List<SkillResponse>>builder()
            .data(skillResponses)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
