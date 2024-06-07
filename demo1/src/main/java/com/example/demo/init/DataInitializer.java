//package com.example.demo.init;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Override
//    public void run(String... args) throws Exception {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScript(new ClassPathResource("sql/projectList.sql"));
//        resourceDatabasePopulator.execute(dataSource);
//    }
//}
