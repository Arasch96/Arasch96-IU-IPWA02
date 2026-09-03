package com.example.require4testing.repository;

import com.example.require4testing.model.TestRun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRunRepository extends JpaRepository<TestRun, Long> {
}
