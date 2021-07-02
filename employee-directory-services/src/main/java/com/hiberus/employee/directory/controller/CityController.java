package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.CityResponseMapper;
import com.hiberus.employee.directory.service.ICityService;
import com.hiberus.employee.directory.vo.City;
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
 * Rest controller for Cities
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/cities")
@Lazy
@Tag(name = "City", description = "The City API")
public class CityController {

    @Lazy
    @Autowired
    @Getter
    private ICityService cityService;

    /**
     * Find all cities
     *
     * @return List of cities
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all cities")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List all cities",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = City.class)))}
        )
    })
    public ResponseEntity<Response<List<City>>> findAll() {
        List<City> cityRespons = CityResponseMapper.MAPPER.toVOList(cityService.findAll());
        Response<List<City>> response = Response.<List<City>>builder()
            .data(cityRespons)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
