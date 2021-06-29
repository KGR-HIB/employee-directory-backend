package com.hiberus.employee.directory;

import com.hiberus.employee.directory.config.EmployeeDirectoryConfiguration;
import com.hiberus.employee.directory.config.Swagger2Config;
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
@Import({EmployeeDirectoryConfiguration.class, Swagger2Config.class})
public class EmployeeDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDirectoryApplication.class);
    }
}
