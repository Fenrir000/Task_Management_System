package com.example.task_management_system.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Запрос на изменение статуса задачи")
public class StatusChangeRequest {

    @NotBlank(message = "Статус не может быть пустым")
    @Schema(description = "Новый статус задачи", example = "COMPLETED")
    private String status;
}