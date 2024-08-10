package com.example.task_management_system.controller;


import com.example.task_management_system.DTO.StatusChangeRequest;
import com.example.task_management_system.DTO.TaskDto;

import com.example.task_management_system.model.Task;
import com.example.task_management_system.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Операции, связанные с задачами")
public class TaskController {
    private final TaskService taskService;
    @PostMapping("/createTask")
    @Operation(summary = "Создать новую задачу")
    public TaskDto createTask(@RequestBody @Valid TaskDto request) {
        Task task = taskService.save(request);
        return convertToDto(task);
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Обновить задачу")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody @Valid TaskDto request) {
        Task updatedTask = taskService.updateTask(id, request);
        return convertToDto(updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить задачу")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    @GetMapping("/get/{id}")
    @Operation(summary = "Получить задачу по ID")
    public TaskDto getTaskById(@PathVariable Long id){
        Task founded= taskService.getTaskById(id);
        return convertToDto(founded);
    }
    @PutMapping("/change-status/{id}")
    @Operation(summary = "Изменить статус задачи")
    public TaskDto changeStatus(@PathVariable Long id, @RequestBody @Valid StatusChangeRequest status) {
        Task updatedTask = taskService.changeStatus(id,status);
        return convertToDto(updatedTask);
    }
    private TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .assigneeId(task.getAssignee().getId())
                .build();
    }
}
