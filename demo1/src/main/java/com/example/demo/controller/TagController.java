package com.example.demo.controller;


import com.example.demo.domain.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

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
