package com.hiberus.employee.directory.controller;

import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.service.ICityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cities")
@Lazy
@Tag(name = "City", description = "City")
public class CityController {

    @Lazy
    @Autowired
    @Getter
    private ICityService cityService;

    @PostMapping
    @Operation(summary = "Save city")
    public CityEntity save(@RequestBody CityEntity city) {
        this.cityService.save(city);
        return city;
    }
}
