package com.example.task_management_system.repos;

import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
