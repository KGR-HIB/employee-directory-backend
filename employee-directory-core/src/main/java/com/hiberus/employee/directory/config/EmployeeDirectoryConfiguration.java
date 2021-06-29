package com.hiberus.employee.directory.config;

import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Employee directory application configuration
 *
 * @author bcueva
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.hiberus.employee.directory.repository"})
@EntityScan(basePackages = {"com.hiberus.employee.directory.entity"})
@ComponentScan(basePackages = {"com.hiberus.employee.directory"})
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "aware")
public class EmployeeDirectoryConfiguration {

    @Bean
    public AuditorAware<Integer> aware() {
        return () -> Optional.of(1);
    }
}
