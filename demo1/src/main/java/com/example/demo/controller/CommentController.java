package com.example.demo.controller;


import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{ProjectId}/Tasks/{taskId}/Comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<Comment> getComments(@PathVariable Long ProjectId, @PathVariable Long taskId) {
        return null;
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long ProjectId, @PathVariable Long taskId, @PathVariable Long commentId) {
        return null;
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment, @PathVariable Long ProjectId, @PathVariable Long taskId) {

    }

    @PutMapping
    public void updateComment(@RequestBody Comment comment, @PathVariable Long ProjectId, @PathVariable Long taskId) {

    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long ProjectId, @PathVariable Long taskId, @PathVariable Long commentId) {

    }


}
