package com.hiberus.employee.directory.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
import com.hiberus.employee.directory.service.IEmployeService;
import com.hiberus.employee.directory.vo.Employe;
import com.hiberus.employee.directory.vo.common.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

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

    /**
     * Crea empleado;
     * 
     * @author acachiguango on 01/07/2021
     * @param request EmployeeEntity
     * @return Response<Employe>
     */
    @PostMapping("/create")
    public ResponseEntity<Response<Employe>> create(@Valid @RequestBody EmployeeEntity request) {
        request.setCreatedByUser(AuthSecurityUtil.getUserLogin().getId());
        this.employeService.create(request);
        return new ResponseEntity<>(
            Response.<Employe>builder().data(Employe.builder().id(request.getId()).build()).build(), HttpStatus.OK);
    }

}
