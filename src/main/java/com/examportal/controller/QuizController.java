package com.examportal.controller;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;
import com.examportal.service.exam.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    private final Logger logger = LoggerFactory.getLogger(QuizController.class);

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.create(quiz);
        logger.info("Quiz created with ID: {}", createdQuiz.getQid());
        return ResponseEntity.ok(createdQuiz);
    }

    @PutMapping("/update")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz updateQuiz = quizService.create(quiz);
        logger.info("Quiz created with ID: {}", updateQuiz.getQid());
        return ResponseEntity.ok(updateQuiz);
    }

    @GetMapping("/{qid}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long qid) {
        Quiz responseQuiz = quizService.getQuizById(qid);
        logger.info("retrived Quiz with ID: {}", qid);
        return ResponseEntity.ok(responseQuiz);
    }

    @DeleteMapping("/{qid}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long qid) {
        quizService.delete(qid);
        logger.info("delete quiz with ID: {}", qid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Quiz> quizzes = quizService.getAll();
        logger.info("All Quizzes retrived sucessfully");
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/category/{cid}")
    public ResponseEntity<?> getQuizzesByCategory(@PathVariable Long cid) {
        Category category = new Category();
        category.setCid(cid);
        List<Quiz> quizzes = quizService.getQuizzesByCategory(category);
        logger.info("Quiz of {} has been retrived", category.getTitle());
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/category1/{cid}")
    public ResponseEntity<List<Quiz>> getQuizzesByCategory2(@PathVariable Long cid) {
        Category category = new Category();
        category.setCid(cid);
        List<Quiz> quizzes = quizService.getQuizzesByCategory(category);
        if (quizzes.isEmpty()) {
            logger.info("No quizzes found for category with ID {}", cid);
            return ResponseEntity.notFound().build();
        } else {
            logger.info("Quizzes for category with ID {}: {}", cid, quizzes);
            return ResponseEntity.ok(quizzes);
        }
    }


    @GetMapping("/active")
    public ResponseEntity<List<Quiz>> getActiveQuizzes() {
        List<Quiz> quizzes = quizService.getActiveQuizzes();
            logger.info("Active quizzes: {}", quizzes);
            return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/category/active/{cid}")
    public ResponseEntity<List<Quiz>> getCategoryAndActiveQuiz(@PathVariable Long cid){
        Category category = new Category();
        category.setCid(cid);
        List<Quiz>quizzes = quizService.getCategoryAndActiveQuiz(category);
            logger.info("Active quizzes for category with ID {}: {}", cid, quizzes);
            return ResponseEntity.ok(quizzes);
        }

}



