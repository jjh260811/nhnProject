package com.example.demo.controller;


import com.example.demo.entity.Project;
import com.example.demo.entity.Tag;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.request.CreateTagRequest;
import com.example.demo.request.UpdateTagRequest;
import com.example.demo.service.TagService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tags")
public class TagController {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @GetMapping
    public List<Tag> getTags(@RequestParam(required = false) Integer page,
                             @RequestParam(required = false) Integer size,
                             @RequestParam(required = false) Integer sort ){
        return tagRepository.findAll();
    }

//    @GetMapping
//    public ModelAndView getAllMilestones(@PathVariable Long projectId) {
//        ModelAndView modelAndView = new ModelAndView("milestone");
//        modelAndView.addObject("milestones", milestoneRepository.findAllByProjectProjectId(projectId));
//        return modelAndView;
//    }

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable Long tagId){
        return tagRepository.findById(tagId).orElse(null);
    }

    @PostMapping
    public Tag createTag(@RequestBody CreateTagRequest createTagRequest){

        Project project = null;
        if(createTagRequest.projectId() != null){
            project = projectRepository.findById(createTagRequest.projectId())
                    .orElse(null);
        }

        Tag tag = new Tag(
                createTagRequest.tagName(),
                project
        );

        return tagRepository.save(tag);
    }

    @PutMapping("/{tagId}")
    public void updateTag(@RequestBody UpdateTagRequest updateTagRequest, @PathVariable Long tagId){
        Project project = null;
        if(updateTagRequest.projectId() != null){
            project = projectRepository.findById(updateTagRequest.projectId())
                    .orElse(null);
        }

        tagRepository.updateByTagId(tagId, updateTagRequest.tagName(), project);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTag(@PathVariable Long tagId){
        if(tagRepository.existsById(tagId)){
            tagRepository.deleteById(tagId);
        }
    }
}
