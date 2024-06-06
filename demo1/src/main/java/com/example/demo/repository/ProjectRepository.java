package com.example.demo.repository;

import com.example.demo.domain.Project;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p")
    @NonNull
    List<Project> findAll();
}
