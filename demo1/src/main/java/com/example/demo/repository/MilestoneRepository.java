package com.example.demo.repository;

import com.example.demo.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findAllByProjectProjectId(Long projectId);



    void deleteAllByProjectProjectIdAndMilestoneId(Long projectId, Long milestoneId);
}
