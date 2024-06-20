package com.example.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Data
@Entity
@Table(name = "problems")
public class Problem {

    @Id
    private String code;
    private String name;
    private String description;
}
