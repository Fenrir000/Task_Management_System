package com.example.task_management_system.service;

import com.example.task_management_system.DTO.CommentDto;
import com.example.task_management_system.model.Comment;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.repos.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;

    /**
     * Сохраняет новый комментарий.
     *
     * @param comment объект комментария для сохранения.
     * @return сохранённый комментарий.
     */
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Получает комментарии задачии с  пагинацией.
     *
     * @param id       идентификатор задачи для которой нужно получить комментарии.
     * @param pageable параметры пагинации
     * @return страница с комментами.
     */
    public Page<Comment> getCommentsByTaskId(Long id, Pageable pageable) {
        return commentRepository.findAllByOwnerId(id, pageable);
    }
    /**
     * Сохраняет новый комментарий на основе данных из CommentDto.
     *
     * @param comment объект DTO с данными комментария.
     * @return сохранённый комментарий.
     */
    public Comment save(CommentDto comment) {
        Comment comm = Comment.builder().comment(comment.getComment()).owner(taskService.getTaskById(comment.getTaskId())).
                build();
        return commentRepository.save(comm);

    }

}
