package com.example.require4testing.controller;

import com.example.require4testing.model.TestCase;
import com.example.require4testing.model.TestExecution;
import com.example.require4testing.model.TestResult;
import com.example.require4testing.model.TestRun;
import com.example.require4testing.model.TestRunStatus;
import com.example.require4testing.model.Tester;
import com.example.require4testing.repository.TestCaseRepository;
import com.example.require4testing.repository.TestExecutionRepository;
import com.example.require4testing.repository.TestRunRepository;
import com.example.require4testing.repository.TesterRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

/**
 * User Story 2: Testmanager:in legt Testläufe an.
 * User Story 4: Testmanager:in ordnet einem Testlauf Testfälle und Tester:in zu.
 * User Story 5: Tester:in versieht zugeordnete Testfälle mit einem Ergebnis.
 */
@Controller
@RequestMapping("/testruns")
public class TestRunController {

    private final TestRunRepository testRunRepository;
    private final TestCaseRepository testCaseRepository;
    private final TesterRepository testerRepository;
    private final TestExecutionRepository testExecutionRepository;

    public TestRunController(TestRunRepository testRunRepository,
                              TestCaseRepository testCaseRepository,
                              TesterRepository testerRepository,
                              TestExecutionRepository testExecutionRepository) {
        this.testRunRepository = testRunRepository;
        this.testCaseRepository = testCaseRepository;
        this.testerRepository = testerRepository;
        this.testExecutionRepository = testExecutionRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("testRuns", testRunRepository.findAll());
        model.addAttribute("statuses", TestRunStatus.values());
        return "testruns/list";
    }

    @PostMapping
    public String create(@RequestParam String name,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate runDate,
                          @RequestParam TestRunStatus status) {
        TestRun testRun = new TestRun();
        testRun.setName(name);
        testRun.setRunDate(runDate);
        testRun.setStatus(status);
        testRunRepository.save(testRun);

        return "redirect:/testruns";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        TestRun testRun = testRunRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Testlauf nicht gefunden: " + id));

        List<TestExecution> executions = testExecutionRepository.findByTestRunId(id);

        // nur Testfälle anbieten, die diesem Testlauf noch nicht zugeordnet sind
        List<Long> assignedTestCaseIds = executions.stream()
                .map(execution -> execution.getTestCase().getId())
                .toList();
        List<TestCase> availableTestCases = testCaseRepository.findAll().stream()
                .filter(testCase -> !assignedTestCaseIds.contains(testCase.getId()))
                .toList();

        model.addAttribute("testRun", testRun);
        model.addAttribute("executions", executions);
        model.addAttribute("availableTestCases", availableTestCases);
        model.addAttribute("testers", testerRepository.findAll());
        model.addAttribute("results", TestResult.values());

        return "testruns/detail";
    }

    @PostMapping("/{id}/assign")
    public String assign(@PathVariable Long id,
                          @RequestParam Long testCaseId,
                          @RequestParam(required = false) Long testerId) {
        TestRun testRun = testRunRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Testlauf nicht gefunden: " + id));
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new IllegalArgumentException("Testfall nicht gefunden: " + testCaseId));

        TestExecution execution = new TestExecution();
        execution.setTestRun(testRun);
        execution.setTestCase(testCase);

        if (testerId != null) {
            Tester tester = testerRepository.findById(testerId)
                    .orElseThrow(() -> new IllegalArgumentException("Tester nicht gefunden: " + testerId));
            execution.setTester(tester);
        }

        testExecutionRepository.save(execution);

        return "redirect:/testruns/" + id;
    }

    @PostMapping("/executions/{executionId}/result")
    public String setResult(@PathVariable Long executionId, @RequestParam TestResult result) {
        TestExecution execution = testExecutionRepository.findById(executionId)
                .orElseThrow(() -> new IllegalArgumentException("Testdurchführung nicht gefunden: " + executionId));
        execution.setResult(result);
        testExecutionRepository.save(execution);

        return "redirect:/testruns/" + execution.getTestRun().getId();
    }
}
