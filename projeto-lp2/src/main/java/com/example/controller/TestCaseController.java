package com.example.controller;

import com.example.model.TestCase;
import com.example.repository.TestCaseRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;

@Controller("/tc")
public class TestCaseController {

    @Inject
    TestCaseRepository testCaseRepository;

    @Post
    public HttpResponse<TestCase> createTestCase(@Body TestCase testCase) {
        return HttpResponse.ok(testCaseRepository.save(testCase));
    }

    @Get("/{problemCode}")
    public List<TestCase> listTestCases(@PathVariable String problemCode) {
        return testCaseRepository.findByProblemCode(problemCode);
    }
}
