package com.example.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Data
@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String problemCode;
    private String filename;

    @Lob
    private String sourceCode;

    private LocalDateTime createdAt = LocalDateTime.now();
    private String status;
}
