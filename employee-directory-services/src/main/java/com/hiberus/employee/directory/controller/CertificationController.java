package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.CertificationResponseMapper;
import com.hiberus.employee.directory.service.ICertificationService;
import com.hiberus.employee.directory.vo.CertificationResponse;
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
 * Rest controller for Certification
 *
 * @author bcueva
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/certifications")
@Lazy
@Tag(name = "Certification", description = "The Certification API")
public class CertificationController {

    @Lazy
    @Autowired
    @Getter
    private ICertificationService certificationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all certifications")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List all certifications",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CertificationResponse.class))) }
        )
    })
    public ResponseEntity<Response<List<CertificationResponse>>> findAll() {
        List<CertificationResponse> certificationResponses = CertificationResponseMapper.MAPPER.toVOList(certificationService.findAll());
        Response<List<CertificationResponse>> response = Response.<List<CertificationResponse>>builder()
            .data(certificationResponses)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
