package com.examportal.repository.exam;

import com.examportal.model.exam.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result,Long> {
}
