package com.example.demo.repository;

import com.example.demo.entity.Milestone;
import com.example.demo.entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.ZonedDateTime;
import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<Milestone> findAllByProjectProjectId(Long projectId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Milestone m " +
            "set m.milestoneName = :milestoneName, m.milestoneProgress = :milestoneProgress, " +
            "m.milestoneStartDate = :milestoneStartDate, m.milestoneEndDate = :milestoneEndDate " +
            "where m.milestoneId = :milestoneId and m.project.projectId = :projectId ")
    void updateProjectByProjectId(@Param("projectId") Long projectId, @Param("milestoneId") Long milestoneId
            , @Param("milestoneName") String milestoneName
            , @Param("milestoneProgress") Integer milestoneProgress
            , @Param("milestoneStartDate") ZonedDateTime milestoneStartDate
            , @Param("milestoneEndDate") ZonedDateTime milestoneEndDate);

}
