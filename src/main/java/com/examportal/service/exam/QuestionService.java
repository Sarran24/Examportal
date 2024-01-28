package com.examportal.service.exam;

import com.examportal.dto.QuestionDto;
import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {

    Question create(Question question);

    Question update(Question question);

    Optional<Question> getById(Long quesId);

    void delete(Long quesId);

    Set<Question> getQuestionOfQuiz(Quiz quiz);


    Map<String, Object> evaluateQuiz(List<Question> questions);
    List<QuestionDto> getRandomQuestionsByQuizId(Long quizId);



}
