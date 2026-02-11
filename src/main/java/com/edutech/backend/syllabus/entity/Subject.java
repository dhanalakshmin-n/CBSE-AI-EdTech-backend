package com.edutech.backend.syllabus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"class_level", "name"})
    }
)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_level", nullable = false)
    private Integer classLevel; // 8–12

    @Column(nullable = false)
    private String name; // Math, Physics
}
