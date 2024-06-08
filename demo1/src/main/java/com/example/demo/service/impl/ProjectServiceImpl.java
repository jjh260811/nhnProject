package com.example.demo.service.impl;

import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

}
