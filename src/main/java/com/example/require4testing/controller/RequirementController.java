package com.example.require4testing.controller;

import com.example.require4testing.model.Requirement;
import com.example.require4testing.repository.RequirementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Story 1: Requirements Engineer legt zu testende Anforderungen an.
 */
@Controller
@RequestMapping("/requirements")
public class RequirementController {

    private final RequirementRepository requirementRepository;

    public RequirementController(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("requirements", requirementRepository.findAll());
        model.addAttribute("requirement", new Requirement());
        return "requirements/list";
    }

    @PostMapping
    public String create(@ModelAttribute Requirement requirement) {
        requirementRepository.save(requirement);
        return "redirect:/requirements";
    }
}
