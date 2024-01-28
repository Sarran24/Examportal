package com.examportal.service.exam;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;

import java.util.List;

public interface QuizService {

    Quiz create(Quiz quiz);
    Quiz update(Quiz quiz);
    Quiz getQuizById(Long qid);
    void delete(Long qid);

    List<Quiz> getAll();
    List<Quiz> getQuizzesByCategory(Category category);

    public List<Quiz> getQuizzesByCategory2(Category category);

    List<Quiz> getActiveQuizzes();

    List<Quiz> getCategoryAndActiveQuiz(Category category);
}
