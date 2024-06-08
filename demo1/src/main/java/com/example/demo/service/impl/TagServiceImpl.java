package com.example.demo.service.impl;

import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
}
