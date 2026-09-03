package com.example.require4testing.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Testlauf, dem Testmanager:innen Testfälle und Tester:innen zuordnen (User Story 2 + 4).
 */
@Entity
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate runDate;

    @Enumerated(EnumType.STRING)
    private TestRunStatus status = TestRunStatus.GEPLANT;

    @OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL)
    private List<TestExecution> executions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public TestRunStatus getStatus() {
        return status;
    }

    public void setStatus(TestRunStatus status) {
        this.status = status;
    }

    public List<TestExecution> getExecutions() {
        return executions;
    }

    public void setExecutions(List<TestExecution> executions) {
        this.executions = executions;
    }
}
