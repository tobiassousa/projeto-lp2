package com.example.repository;

import com.example.model.TestCase;
import com.example.model.TestCaseId;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, TestCaseId> {
    List<TestCase> findByProblemCode(String problemCode);
}
