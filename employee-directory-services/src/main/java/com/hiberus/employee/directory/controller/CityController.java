package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.mapper.CityResponseMapper;
import com.hiberus.employee.directory.service.ICityService;
import com.hiberus.employee.directory.vo.CityResponse;
import com.hiberus.employee.directory.vo.common.Response;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "City")
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
    @GetMapping
    public ResponseEntity<Response<List<CityResponse>>> findAll() {
        List<CityResponse> cityResponses = CityResponseMapper.MAPPER.toVOList(cityService.findAll());
        Response<List<CityResponse>> response = Response.<List<CityResponse>>builder()
            .data(cityResponses)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save city")
    public CityEntity save(@RequestBody CityEntity city) {
        this.cityService.save(city);
        return city;
    }
}
