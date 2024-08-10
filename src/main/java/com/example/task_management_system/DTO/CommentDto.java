package com.example.task_management_system.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "dto комментария")
public class CommentDto {
    @Schema(description = "Comment", example = "Это пример комментария.")
    @NotBlank(message = "Комментарий не может быть пустым")
    private String comment;
    @Schema(description = "TaskId", example = "1L")
    @NotBlank(message = "Должна быть задача, которую вы комментируете")
    private Long taskId;
}
