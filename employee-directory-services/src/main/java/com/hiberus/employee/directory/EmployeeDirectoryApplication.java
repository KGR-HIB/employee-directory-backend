package com.hiberus.employee.directory;

import com.hiberus.employee.directory.config.EmployeeDirectoryConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

/**
 * Application
 *
 * @author bcueva
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.hiberus.employee.directory"})
@EntityScan(basePackages = "com.hiberus.employee.directory")
@Import({EmployeeDirectoryConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Employee Directory", version = "1.0", description = "Employee Directory"))
public class EmployeeDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDirectoryApplication.class);
    }
}
