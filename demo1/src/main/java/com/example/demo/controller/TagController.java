package com.example.demo.controller;


import com.example.demo.domain.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<Tag> getTags(){
        return null;
    }

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable Long tagId){
        return null;
    }

    @PostMapping
    public void createTag(@RequestBody Tag tag){

    }

    @PutMapping
    public void updateTag(@RequestBody Tag tag){

    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable Long tagId){

    }
}
