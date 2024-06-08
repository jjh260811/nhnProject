package com.example.demo.service.impl;

import com.example.demo.repository.MilestoneRepository;
import com.example.demo.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;

}
