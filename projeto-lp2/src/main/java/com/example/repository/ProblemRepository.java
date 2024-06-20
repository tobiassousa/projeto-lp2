package com.example.repository;

import com.example.model.Problem;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, String> {
}
