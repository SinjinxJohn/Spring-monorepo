package com.example.todo.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;


    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = true,unique = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
   private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
