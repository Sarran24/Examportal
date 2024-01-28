package com.examportal.service.exam;

import com.examportal.controller.QuestionController;
import com.examportal.dto.QuestionDto;
import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.repository.exam.QuestionRepository;
import com.examportal.repository.exam.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> getById(Long quesId) {
        return questionRepository.findById(quesId);
    }

    @Override
    public void delete(Long quesId) {
        questionRepository.deleteById(quesId);
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

    @Override
    public Map<String,Object> evaluateQuiz(List<Question> questions) {
        double marksGot = 0;
        int correctMarks = 0;
        int attempted = 0;
        double singleMarks;

        logger.info("questions are: {}", questions);
        for (Question q : questions) {
            logger.info("Given Answers is: {}", q.getGivenAnswer());
            Optional<Question> question = this.getById(q.getQuesId());
            if (question.get().getAnswer().equals(q.getGivenAnswer())) {
                //correct
                correctMarks++;
                singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksGot += singleMarks;
                logger.info("single marks: {}",singleMarks);

                logger.info("marksgot: {}",marksGot);
            }
            if (q.getGivenAnswer() != null) {
                attempted++;
            }
        }
        Map<String,Object> values = Map.of("marksGot",marksGot,"correctMarks",correctMarks,"attempted",attempted);
        String username=questions.get(0).getUsername();
//        Result result = new Result();
//        result.setCorrectMarks(correctMarks);
//        result.setAttempted(attempted);
//        result.setMarksGot(marksGot);
//        result.setUserid(username);
//        resultRepository.save(result);

        return values;
    }

    @Override
    public List<QuestionDto> getRandomQuestionsByQuizId(Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question> setOfQuestion = getQuestionOfQuiz(quiz);

        int numberOfQuestions;
        try {
            numberOfQuestions = Integer.parseInt(setOfQuestion.stream()
                    .findFirst()
                    .map(Question::getQuiz)
                    .map(Quiz::getNoOfQuestion)
                    .orElse("0"));
        } catch (NumberFormatException e) {
            numberOfQuestions = 0; // default value if the string is not a valid integer
        }

        List<QuestionDto> questionsList = setOfQuestion.stream()
                .map(QuestionDto::new)
                .collect(Collectors.toList());

        if (questionsList.size() > numberOfQuestions) {
            questionsList = questionsList.subList(0, numberOfQuestions + 1);
        }

        Collections.shuffle(questionsList);
        return questionsList;
    }


}
