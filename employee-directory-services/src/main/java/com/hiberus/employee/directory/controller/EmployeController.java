package com.hiberus.employee.directory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hiberus.employee.directory.entity.EmployeeEntity;
import com.hiberus.employee.directory.security.AuthSecurityUtil;
import com.hiberus.employee.directory.service.IEmployeService;
import com.hiberus.employee.directory.service.IUserService;
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

    @Lazy
    @Autowired
    @Getter
    private IUserService userService;

    /**
     * Crea empleado.
     * 
     * @author acachiguango on 01/07/2021
     * @param request EmployeeEntity
     * @return Response<Employe>
     */
    @PostMapping("/createOrUpdate")
    public ResponseEntity<Response<Employe>> createOrUpdate(@RequestBody EmployeeEntity request) {
        if (null == request.getUser().getId() && this.userService.existsByMail(request.getUser().getEmail())) {
            return ResponseEntity.ok()
                .body(Response.<Employe>builder().code(1).message("User already exists.").build());
        }
        request.setCreatedByUser(AuthSecurityUtil.getUserLogin().getId());
        this.employeService.createOrUpdate(request);
        return ResponseEntity.ok().body(Response.<Employe>builder().data(Employe.builder().id(request.getId()).build())
            .code(200).message("success").build());
    }
}
