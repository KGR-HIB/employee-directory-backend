package com.hiberus.employee.directory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.employee.directory.config.EmployeeDirectoryConfiguration;
import com.hiberus.employee.directory.security.WebSecurityConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Application
 *
 * @author bcueva
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = { "com.hiberus.employee.directory" })
@EntityScan(basePackages = "com.hiberus.employee.directory")
@Import({ EmployeeDirectoryConfiguration.class, WebSecurityConfig.class })
@OpenAPIDefinition(info = @Info(title = "Employee Directory", version = "1.0", description = "Employee Directory"))
public class EmployeeDirectoryApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeDirectoryApplication.class);
    }

    /**
     * ObjectMapper.
     * 
     * @author acachiguango on 01/07/2021
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
