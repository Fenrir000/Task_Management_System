package com.example.task_management_system.DTO;

import com.example.task_management_system.model.Priority;
import com.example.task_management_system.model.Status;
import com.example.task_management_system.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание таски")
@Builder
public class TaskDto {

    @NotBlank(message = "Заголовок не может быть пустым")
    @Schema(description = "Заголовок", example = "Приготовить пирог")
    private String title;

    @Schema(description = "Описание", example = "Достаньте ингредиенты, приготовьте приборы, начните готовку.")
    private String description;

    @NotNull(message = "Статус не может быть пустым")
    @Schema(description = "Статус", example = "PENDING")
    private Status status;

    @Schema(description = "Приоритет", example = "MEDIUM")
    private Priority priority;

    @NotNull(message = "Исполнитель не может быть пустым")
    @Schema(description = "Исполнитель", example = "1L")
    private Long assigneeId;
}
