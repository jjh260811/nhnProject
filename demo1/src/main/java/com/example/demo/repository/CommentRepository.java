package com.example.demo.repository;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Project;
import com.example.demo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    @Transactional
    @Query("update Comment t set t.commentContent = :commentContent, t.task = :task where t.commentId = :commentId")
    void updateByCommentId(@Param("commentId") Long commentId,
                       @Param("commentContent") String commentContent,
                       @Param("task") Task task);

    List<Comment> findAllByTaskTaskId(Long taskId);
}
