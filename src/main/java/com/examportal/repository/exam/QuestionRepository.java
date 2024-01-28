package com.examportal.repository.exam;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
