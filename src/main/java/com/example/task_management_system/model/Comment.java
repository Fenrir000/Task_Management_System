package com.example.task_management_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task owner;
}
