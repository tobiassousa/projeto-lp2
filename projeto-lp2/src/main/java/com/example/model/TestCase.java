package com.example.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Data
@Entity
@Table(name = "test_cases")
@IdClass(TestCaseId.class)
public class TestCase {

    @Id
    private String problemCode;

    @Id
    private String inputFileName;

    private String expectedOutputFileName;
}
