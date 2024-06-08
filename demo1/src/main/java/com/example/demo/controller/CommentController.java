package com.example.demo.controller;


import com.example.demo.entity.Comment;
import com.example.demo.entity.Task;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.request.CreateCommentRequest;
import com.example.demo.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/projects/{projectId}/tasks/{taskId}/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @GetMapping
    public List<Comment> getComments(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size,
                                     @RequestParam(required = false) Integer sort,
                                     @PathVariable Long projectId, @PathVariable Long taskId) {
        return commentRepository.findAllByTaskTaskId(taskId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @PostMapping
    public Comment createComment(@RequestBody CreateCommentRequest createCommentRequest, @PathVariable Long projectId, @PathVariable Long taskId) {
        Task task = null;
        if(taskId != null){
            task = taskRepository.findById(taskId)
                    .orElse(null);
        }
        Comment comment = new Comment(
                createCommentRequest.commentContent(),
                task
        );

        return commentRepository.save(comment);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@RequestBody UpdateCommentRequest updateCommentRequest, @PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId) {
        Task task = null;
        if(taskId != null){
            task = taskRepository.findById(taskId)
                    .orElse(null);
        }

        commentRepository.updateByCommentId(commentId, updateCommentRequest.commentContent(), task);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId) {
        if(commentRepository.existsById(commentId)){
            commentRepository.deleteById(commentId);
        }
    }


}
