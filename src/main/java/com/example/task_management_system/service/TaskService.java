package com.example.task_management_system.service;

import com.example.task_management_system.DTO.StatusChangeRequest;
import com.example.task_management_system.DTO.TaskDto;
import com.example.task_management_system.model.Status;
import com.example.task_management_system.model.Task;
import com.example.task_management_system.model.User;
import com.example.task_management_system.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    /**
     * Сохранение таски
     *
     * @return сохраненная таска
     */
    public Task save(Task task) {
        return taskRepository.save(task);
    }
    /**
     * Получает задачу по её идентификатору.
     *
     * @param id идентификатор задачи.
     * @return задача с указанным идентификатором.
     * @throws IllegalArgumentException если задача не найдена.
     */
    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

    }
    /**
     * Сохраняет новую задачу на основе данных из TaskDto.
     *
     * @param taskDto объект DTO с данными задачи.
     * @return сохранённая задача.
     */
    public Task save(TaskDto taskDto) {
        User curr= userService.getCurrentUser();
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .priority(taskDto.getPriority())
                .author(curr)
                .assignee(userService.getById(taskDto.getAssigneeId()))
                .build();
       return taskRepository.save(task);


    }
    /**
     * Обновляет задачу по её идентификатору.
     *
     * @param id идентификатор задачи.
     * @param taskDto объект DTO с новыми данными задачи.
     * @return обновлённая задача.
     * @throws RuntimeException если текущий пользователь не является автором задачи.
     */
    @Transactional
    public Task updateTask(Long id, TaskDto taskDto) {
        Task task= checkAuthority( id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setPriority(taskDto.getPriority());

        if (taskDto.getAssigneeId() != null) {
            User assignee = userService.getById(taskDto.getAssigneeId());
            task.setAssignee(assignee);
        }

        return taskRepository.save(task);
    }
    /**
     * Удаляет таску по её идентификатору.
     *
     * @param id идентификатор задачи.
     * @throws RuntimeException если текущий пользователь не является автором задачи.
     */
    @Transactional
    public void deleteTask(Long id) {
        Task task=checkAuthority( id);
        taskRepository.delete(task);
    }
    /**
     * Проверяет права доступа текущего пользователя к задаче.
     *
     * @param id идентификатор задачи.
     * @return задача с указанным идентификатором.
     * @throws RuntimeException если текущий пользователь не является автором задачи.
     */
    public Task checkAuthority(Long id){
        User curr= userService.getCurrentUser();
        Task task = getTaskById( id);
        if(!Objects.equals(curr.getId(), task.getAuthor().getId())){
            throw new RuntimeException("You are not author of this task");
        }
        return task;
    }

    /**
     * Изменяет статус задачи.
     *
     * @param id идентификатор задачи.
     * @param status объект запроса на изменение статуса.
     * @return обновлённая задача.
     * @throws RuntimeException если текущий пользователь не является автором или исполнителем задачи.
     */
    public Task changeStatus(Long id, StatusChangeRequest status){
        User curr= userService.getCurrentUser();
        Task task = getTaskById( id);
        if(!Objects.equals(curr.getId(), task.getAuthor().getId()) && !Objects.equals(curr.getId(), task.getAssignee().getId()) ){
            throw new RuntimeException("You are not author or assignee  of this task");
        }

        task.setStatus(Status.valueOf(status.getStatus()));

      return  taskRepository.save(task);
    }
}
