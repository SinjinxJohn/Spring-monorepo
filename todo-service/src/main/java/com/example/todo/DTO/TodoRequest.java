package com.example.todo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
