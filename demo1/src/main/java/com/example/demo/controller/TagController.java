package com.example.demo.controller;


import com.example.demo.entity.Project;
import com.example.demo.entity.Tag;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.request.CreateTagRequest;
import com.example.demo.request.UpdateTagRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/projects/{projectId}/tags")
public class TagController {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @GetMapping
    public ModelAndView getTags(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer size,
                                @RequestParam(required = false) Integer sort,
                                @PathVariable Long projectId,
                                @PathVariable Long userId){
        List<Tag> tags = tagRepository.findByProjectProjectId(projectId);

        ModelAndView modelAndView = new ModelAndView("tagAddView");
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("projectId", projectId);
        modelAndView.addObject("tags", tags);

        return modelAndView;
    }

    @GetMapping("/{tagId}")
    public Tag getTag(@PathVariable Long tagId){
        return tagRepository.findById(tagId).orElse(null);
    }

    @PostMapping
    public String createTag(@ModelAttribute @RequestBody CreateTagRequest createTagRequest, @PathVariable Long userId, @PathVariable Long projectId){

        Project project = null;
        if(projectId != null){
            project = projectRepository.findById(projectId)
                    .orElse(null);
        }

        Tag tag = new Tag(
                createTagRequest.tagName(),
                project
        );

        tagRepository.save(tag);

        return "redirect:/users/" + userId + "/projects/" + project.getProjectId();
    }

    @PutMapping("/{tagId}")
    public void updateTag(@RequestBody UpdateTagRequest updateTagRequest, @PathVariable Long projectId, @PathVariable Long tagId){
        Project project = null;
        if(projectId != null){
            project = projectRepository.findById(projectId)
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
