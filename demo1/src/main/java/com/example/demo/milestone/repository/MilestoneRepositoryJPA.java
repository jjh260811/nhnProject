package com.example.demo.milestone.repository;

import com.example.demo.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepositoryJPA extends JpaRepository<Milestone, Long> {
}
