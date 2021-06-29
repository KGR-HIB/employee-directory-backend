package com.hiberus.employee.directory.controller;

import java.util.List;
import com.hiberus.employee.directory.mapper.PositionResponseMapper;
import com.hiberus.employee.directory.service.IPositionService;
import com.hiberus.employee.directory.vo.PositionResponse;
import com.hiberus.employee.directory.vo.common.Response;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response<List<PositionResponse>>> findAll() {
        List<PositionResponse> positionResponses = PositionResponseMapper.MAPPER.toVOList(positionService.findAll());
        Response<List<PositionResponse>> response = Response.<List<PositionResponse>>builder()
            .data(positionResponses)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
