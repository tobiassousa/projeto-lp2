package com.example.controller;

import com.example.model.Problem;
import com.example.repository.ProblemRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;

@Controller("/activity")
public class ProblemController {

    @Inject
    ProblemRepository problemRepository;

    @Post
    public HttpResponse<Problem> createProblem(@Body Problem problem) {
        return HttpResponse.ok(problemRepository.save(problem));
    }

    @Get
    public List<Problem> listProblems() {
        return problemRepository.findAll();
    }
}
