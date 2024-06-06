package com.example.demo.controller;


import com.example.demo.domain.Tag;
import com.example.demo.repository.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public String getTags(Model model){
        model.addAttribute("tags", tagRepository.findAll());
        return "tags";
    }

    @GetMapping("/{tagId}")
    public String getTag(@PathVariable Long tagId, Model model){
        Tag tag = tagRepository.findById(tagId).orElse(null);
        model.addAttribute("tag", tag);

        return "tag";
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
