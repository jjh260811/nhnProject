package com.example.demo.config;

import com.example.demo.entity.Project;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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
            Project project = new Project(
                    "projectTest",
                    Project.ProjectStatus.ACTIVE
            );

            restTemplate.postForObject(url, project, Project.class);
        };
    }


}
