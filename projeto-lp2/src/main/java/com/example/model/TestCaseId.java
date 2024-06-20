package com.example.model;

import java.io.Serializable;
import lombok.Data;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Data
public class TestCaseId implements Serializable {
    private String problemCode;
    private String inputFileName;
}
