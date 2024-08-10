package com.example.task_management_system.repos;

import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByOwnerId(Long ownerId, Pageable pageable);
}
