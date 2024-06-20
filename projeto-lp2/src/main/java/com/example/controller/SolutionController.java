package com.example.controller;

import com.example.model.Solution;
import com.example.model.TestCase;
import com.example.repository.SolutionRepository;
import com.example.repository.TestCaseRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.multipart.CompletedFileUpload;

import jakarta.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/activity/solution")
public class SolutionController {

    @Inject
    SolutionRepository solutionRepository;

    @Inject
    TestCaseRepository testCaseRepository;

    @Post(consumes = "multipart/form-data")
    public HttpResponse<Solution> submitSolution(@Part("file") CompletedFileUpload file,
                                                 @Part("author") String author,
                                                 @Part("problemCode") String problemCode) {
        Solution solution = new Solution();
        solution.setAuthor(author);
        solution.setProblemCode(problemCode);
        solution.setFilename(file.getFilename());

        try {
            File tempDir = new File("solutions/" + problemCode);
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }

            String filePath = tempDir.getAbsolutePath() + "/" + file.getFilename();

            try (OutputStream os = new FileOutputStream(filePath)) {
                os.write(file.getBytes());
            }
            solution.setSourceCode(new String(file.getBytes()));

            String result = executeSolution(solution);
            solution.setStatus(result);

            return HttpResponse.ok(solutionRepository.save(solution));

        } catch (IOException e) {
            e.printStackTrace();
            return HttpResponse.serverError();
        }
    }

    @Get("/{problemCode}")
    public List<Solution> listSolutions(@PathVariable String problemCode) {
        return solutionRepository.findByProblemCode(problemCode);
    }

    private String executeSolution(Solution solution) {
        try {
            File tempDir = new File("temp/" + solution.getProblemCode());
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }

            String javaFilename = tempDir.getAbsolutePath() + "/" + solution.getFilename();
            try (FileWriter fileWriter = new FileWriter(javaFilename)) {
                fileWriter.write(solution.getSourceCode());
            }

            ProcessBuilder compileBuilder = new ProcessBuilder("javac", javaFilename);
            Process compileProcess = compileBuilder.start();
            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                return "FAIL - Compilation Error";
            }

            List<TestCase> testCases = testCaseRepository.findByProblemCode(solution.getProblemCode());
            for (TestCase testCase : testCases) {
                String inputFilePath = "testcases/" + testCase.getInputFileName();
                String expectedOutputFilePath = "testcases/" + testCase.getExpectedOutputFileName();

                ProcessBuilder runBuilder = new ProcessBuilder("java", "-cp", tempDir.getAbsolutePath(), solution.getFilename().replace(".java", ""));
                runBuilder.redirectInput(new File(inputFilePath));
                Process runProcess = runBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                runProcess.waitFor();

                String expectedOutput = Files.lines(Paths.get(expectedOutputFilePath)).collect(Collectors.joining("\n"));
                if (!output.toString().trim().equals(expectedOutput.trim())) {
                    return "FAIL - Test Case Failed";
                }
            }

            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL - Runtime Error";
        }
    }
}
