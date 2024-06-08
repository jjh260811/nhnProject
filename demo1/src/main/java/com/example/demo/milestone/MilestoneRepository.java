package com.example.demo.milestone;

import com.example.demo.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findAllByProjectProjectId(Long projectId);

}
