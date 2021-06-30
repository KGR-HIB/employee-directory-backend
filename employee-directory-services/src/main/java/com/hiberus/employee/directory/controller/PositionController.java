package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.PositionResponseMapper;
import com.hiberus.employee.directory.service.IPositionService;
import com.hiberus.employee.directory.vo.PositionResponse;
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
 * Rest controller for position
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/positions")
@Lazy
@Tag(name = "Position", description = "The Position API")
public class PositionController {

    @Lazy
    @Autowired
    @Getter
    private IPositionService positionService;

    /**
     * Find all positions
     *
     * @return List of Positions
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all positions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List all positions",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PositionResponse.class)))}
        )
    })
    public ResponseEntity<Response<List<PositionResponse>>> findAll() {
        List<PositionResponse> positionResponses = PositionResponseMapper.MAPPER.toVOList(positionService.findAll());
        Response<List<PositionResponse>> response = Response.<List<PositionResponse>>builder()
            .data(positionResponses)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
