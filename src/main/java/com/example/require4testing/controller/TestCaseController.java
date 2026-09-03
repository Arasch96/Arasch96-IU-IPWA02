package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.model.TestCase;
import com.example.require4testing.repository.RequirementRepository;
import com.example.require4testing.repository.TestCaseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User Story 3: Testfallersteller:in legt zu einer Anforderung Testfälle an.
 */
@Controller
@RequestMapping("/testcases")
public class TestCaseController {

    private final TestCaseRepository testCaseRepository;
    private final RequirementRepository requirementRepository;

    public TestCaseController(TestCaseRepository testCaseRepository,
                               RequirementRepository requirementRepository) {
        this.testCaseRepository = testCaseRepository;
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("testCases", testCaseRepository.findAll());
        model.addAttribute("requirements", requirementRepository.findAll());
        return "testcases/list";
    }

    @PostMapping
    public String create(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam Long requirementId) {
        Requirement requirement = requirementRepository.findById(requirementId)
                .orElseThrow(() -> new IllegalArgumentException("Anforderung nicht gefunden: " + requirementId));

        TestCase testCase = new TestCase();
        testCase.setTitle(title);
        testCase.setDescription(description);
        testCase.setRequirement(requirement);
        testCaseRepository.save(testCase);

        return "redirect:/testcases";
    }
}
