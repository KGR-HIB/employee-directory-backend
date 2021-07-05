package com.hiberus.employee.directory.config;

import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Employee directory application configuration
 *
 * @author bcueva
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.hiberus.employee.directory.repository" })
@EntityScan(basePackages = { "com.hiberus.employee.directory.entity" })
@ComponentScan(basePackages = { "com.hiberus.employee.directory" })
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "aware")
public class EmployeeDirectoryConfiguration {

    @Bean
    public AuditorAware<Integer> aware() {
        return () -> Optional.of(1);
    }

    @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        int saltLength = 16; // salt length in bytes
        int hashLength = 32; // hash length in bytes
        int parallelism = 1; // currently not supported by Spring Security
        int memory = 4096; // memory costs
        int iterations = 3;
        Argon2PasswordEncoder argon2PasswordEncoder =
            new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
        return argon2PasswordEncoder;
    }

}
