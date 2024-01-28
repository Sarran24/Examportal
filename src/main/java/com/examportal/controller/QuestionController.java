package com.examportal.controller;

import com.examportal.dto.QuestionDto;
import com.examportal.model.User;
import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.Result;
import com.examportal.repository.exam.QuizRepository;
import com.examportal.repository.exam.ResultRepository;
import com.examportal.service.exam.QuestionService;
import com.examportal.service.exam.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultRepository resultRepository;

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        logger.info("Creating question with title: {}", question.getQuiz().getTitle());
        Question createdQuestion = questionService.create(question);
        return ResponseEntity.ok(createdQuestion);
    }

    @PutMapping("/{quesId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long quesId, @RequestBody Question question) {
        logger.info("Updating question with ID: {}", quesId);
        question.setQuesId(quesId);
        Question updatedQuestion = questionService.update(question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long quesId) {
        logger.info("Getting question with ID: {}", quesId);
        Optional<Question> optionalQuestion = questionService.getById(quesId);
        if (optionalQuestion.isPresent()) {
            return ResponseEntity.ok(optionalQuestion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{quesId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long quesId) {
        logger.info("Deleting question with ID: {}", quesId);
        questionService.delete(quesId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getByQuiz(@PathVariable Long quizId) throws Exception {
//        Quiz quiz = new Quiz();
//        quiz.setQid(quizId);
//        Set<Question> questionOfQuiz = questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionOfQuiz);
//        ****************************************
        Quiz quiz = quizService.getQuizById(quizId);
        List<Question> questions = quiz.getQuestions();
        List questionList = new ArrayList(questions);
        if (questionList.size() > Integer.parseInt(quiz.getNoOfQuestion())) {
            questionList = questionList.subList(0, Integer.parseInt(quiz.getNoOfQuestion() + 1));
        }
        Collections.shuffle(questionList);
        return ResponseEntity.ok(questionList);


//
//            logger.info("Fetching quiz with id {}", quizId);
//            Quiz quiz = quizRepository.findById(quizId).orElse(null);
//            if (quiz != null) {
//                logger.info("Quiz found: {}", quiz.getTitle());
//                Set<Question> questions = quiz.getQuestion();
//                logger.info("Questions for quiz {}: {}", quiz.getTitle(), questions.size());
//                for (Question question : questions) {
//                    logger.info("Question {}: {}", question.getQuesId(), question.getContent());
//                }
//            } else {
//                logger.info("Quiz not found for id {}", quizId);
//            }
//            return quiz;
    }

    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) {
        logger.info("Fetching quiz with id {}", quizId);
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            logger.info("Quiz found: {}", quiz.getTitle());
            List<Question> questions = quiz.getQuestions();
            logger.info("Questions for quiz {}: {}", quiz.getTitle(), questions.size());
            for (Question question : questions) {
                logger.info("Question {}: {}", question.getQuesId(), question.getContent());
            }
            return ResponseEntity.ok().body(quiz);
        } else {
            logger.info("Quiz not found for id {}", quizId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/quizz-admin/{quizId}")
    public ResponseEntity<?> getByQuizzAdmin(@PathVariable Long quizId) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question> setOfQestion = questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(setOfQestion);
    }


//    @GetMapping("/quizz/{quizId}")
//    public ResponseEntity<?> getByQuizz(@PathVariable Long quizId) throws Exception {
//        Quiz quiz = new Quiz();
//        quiz.setQid(quizId);
//        Set<Question> setOfQuestion = questionService.getQuestionOfQuiz(quiz);
//
//        int numberOfQuestions;
//        try {
//            numberOfQuestions = Integer.parseInt(setOfQuestion.stream()
//                    .findFirst()
//                    .map(Question::getQuiz)
//                    .map(Quiz::getNoOfQuestion)
//                    .orElse("0"));
//        } catch (NumberFormatException e) {
//            numberOfQuestions = 0; // default value if the string is not a valid integer
//        }
//
//
//        List<Question> questionsList = new ArrayList<>(setOfQuestion);
//        if (questionsList.size() > numberOfQuestions) {
//            questionsList = questionsList.subList(0, numberOfQuestions + 1);
//        }
//
//        Collections.shuffle(questionsList);
//        return ResponseEntity.ok(questionsList);
//    }

/*    @GetMapping("/quizz/{quizId}")
    public ResponseEntity<?> getByQuizz(@PathVariable Long quizId) throws Exception {
        Quiz quiz = new Quiz();
        quiz.setQid(quizId);
        Set<Question> setOfQuestion = questionService.getQuestionOfQuiz(quiz);

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
        return ResponseEntity.ok(questionsList);
    }*/


    @GetMapping("/quizz/{quizId}")
    public ResponseEntity<?> getByQuizz(@PathVariable Long quizId) throws Exception {
        List<QuestionDto> questionsList = questionService.getRandomQuestionsByQuizId(quizId);
        return ResponseEntity.ok(questionsList);
    }



//    @PostMapping("eval-quiz")
//    public ResponseEntity<?> evaluateQuestion(@RequestBody List<Question> questions) {
//        double marksGot = 0;
//        int correctMarks = 0;
//        int attempted = 0;
//        double singleMarks;
//
//        logger.info("questions are: {}", questions);
//        for (Question q : questions) {
//            logger.info("Given Answers is: {}", q.getGivenAnswer());
//            Optional<Question> question = this.questionService.getById(q.getQuesId());
//            if (question.get().getAnswer().equals(q.getGivenAnswer())) {
//                //correct
//                correctMarks++;
//                singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
//                marksGot += singleMarks;
//                logger.info("single marks: {}",singleMarks);
//
//                logger.info("marksgot: {}",marksGot);
//            }
//            if (q.getGivenAnswer() != null) {
//                attempted++;
//            }
//        }
//        Map<String,Object> values = Map.of("marksGot",marksGot,"correctMarks",correctMarks,"attempted",attempted);
//
//        return ResponseEntity.ok(values);
//    }

//    @PostMapping("eval-quiz")
//    public ResponseEntity<?> evaluateQuestionSaveResultInDB(@RequestBody List<Question> questions) {
//        double marksGot = 0;
//        int correctMarks = 0;
//        int attempted = 0;
//        double singleMarks;
//
//        logger.info("questions are: {}", questions);
//        for (Question q : questions) {
//            logger.info("Given Answers is: {}", q.getGivenAnswer());
//            Optional<Question> question = this.questionService.getById(q.getQuesId());
//            if (question.get().getAnswer().equals(q.getGivenAnswer())) {
//                //correct
//                correctMarks++;
//                singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
//                marksGot += singleMarks;
//                logger.info("single marks: {}",singleMarks);
//
//                logger.info("marksgot: {}",marksGot);
//            }
//            if (q.getGivenAnswer() != null) {
//                attempted++;
//            }
//        }
//        Map<String,Object> values = Map.of("marksGot",marksGot,"correctMarks",correctMarks,"attempted",attempted);
//        String username=questions.get(0).getUsername();
//        Result result = new Result();
//        result.setCorrectMarks(correctMarks);
//        result.setAttempted(attempted);
//        result.setMarksGot(marksGot);
//        result.setUserid(username);
//        resultRepository.save(result);
//
//        return ResponseEntity.ok(values);
//    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
        logger.info("Evaluating quiz with questions: {}", questions);

        Map<String, Object> result = questionService.evaluateQuiz(questions);

        logger.info("Quiz evaluation result: {}", result);

        return ResponseEntity.ok(result);
    }


}


