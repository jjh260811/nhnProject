package com.example.demo.controller;


import com.example.demo.domain.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects/{ProjectId}/Tasks/{taskId}/Comments")
public class CommentController {

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
