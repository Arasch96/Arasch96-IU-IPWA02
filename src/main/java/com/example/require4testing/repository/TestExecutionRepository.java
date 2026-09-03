package com.example.require4testing.repository;

import com.example.require4testing.model.TestExecution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

    List<TestExecution> findByTestRunId(Long testRunId);
}
