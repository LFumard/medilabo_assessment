package com.lfumard.medilabo_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedilaboAssessmentApplication {

    public static void main(String[] args) {

        SpringApplication.run(MedilaboAssessmentApplication.class, args);
    }
}
