package com.boob.medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@EntityScan(basePackages = "com.boob.medical.entity")
@EnableJpaRepositories(basePackages = "com.boob.medical.dao")
@SpringBootApplication
public class MedicalScienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalScienceApplication.class, args);
    }

}
