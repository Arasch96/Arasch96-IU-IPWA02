package com.example.require4testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Verknüpft einen Testfall innerhalb eines Testlaufs mit einem/einer Tester:in
 * und dessen Ergebnis (User Story 4 + 5). Quasi die Zwischentabelle
 * TestRun <-> TestCase, nur mit zusätzlichen Spalten.
 */
@Entity
public class TestExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_run_id")
    private TestRun testRun;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private Tester tester;

    @Enumerated(EnumType.STRING)
    private TestResult result = TestResult.OFFEN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public TestResult getResult() {
        return result;
    }

    public void setResult(TestResult result) {
        this.result = result;
    }
}
