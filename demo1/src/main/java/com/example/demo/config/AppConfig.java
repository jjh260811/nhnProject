package com.example.demo.config;

import com.example.demo.entity.Project;
import com.example.demo.request.CreateProjectRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
    public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
        return args -> {
            String url = "http://localhost:8080/users/1/projects";

            CreateProjectRequest request = new CreateProjectRequest(
                    "projectTest",
                    Project.ProjectStatus.ACTIVE,
                    null,
                    null,
                    null,
                    null
            );

            restTemplate.postForObject(url, request, CreateProjectRequest.class);
        };
    }


}
