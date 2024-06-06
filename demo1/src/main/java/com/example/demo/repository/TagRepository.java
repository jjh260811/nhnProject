package com.example.demo.repository;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import com.example.demo.entity.Tag;
import com.example.demo.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Modifying
    @Transactional
    @Query("update Tag t set t.tagName = :tagName, t.project = :project where t.tagId = :tagId")
    void updateByTagId(@Param("tagId") Long tagId,
                        @Param("tagName") String tagName,
                        @Param("project") Project project);

}
