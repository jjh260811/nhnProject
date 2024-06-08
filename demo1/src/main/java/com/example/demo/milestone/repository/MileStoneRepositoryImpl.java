package com.example.demo.milestone.repository;

import com.example.demo.entity.Milestone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MileStoneRepositoryImpl implements MilestoneRepository{

    private final MilestoneRepositoryJPA milestoneRepositoryJPA;

    @Override
    public void isExist(Milestone milestone) {
//        milestoneRepositoryJPA.existsById()
    }
}
