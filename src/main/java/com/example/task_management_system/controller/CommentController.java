package com.example.task_management_system.controller;

import com.example.task_management_system.DTO.CommentDto;
import com.example.task_management_system.DTO.TaskDto;
import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.service.CommentService;
import com.example.task_management_system.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "Комментарий", description = "Операции, связанные с комментариями")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/get/{id}")
    @Operation(summary = "Получить комментарии по ID задачи")
    public List<CommentDto> getCommentsByTaskId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> commentsPage = commentService.getCommentsByTaskId(id, pageable);

        return commentsPage.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @PostMapping("/create-сomment")
    @Operation(summary = "Создать новый комментарий")
    public CommentDto createComment(@RequestBody CommentDto request) {

        Comment comm = commentService.save(request);
        return convertToDto(comm);
    }
    private CommentDto convertToDto(Comment comment) {
        return CommentDto.builder()
                .comment(comment.getComment())
                .taskId(comment.getOwner().getId())
                .build();
    }
}
