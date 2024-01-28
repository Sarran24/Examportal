package com.examportal.service.exam;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;
import com.examportal.repository.exam.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz create(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz update(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuizById(Long qid) {
        return quizRepository.findById(qid).get();
    }

    @Override
    public void delete(Long qid) {
        quizRepository.deleteById(qid);
    }

    @Override
    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    @Override
    public List<Quiz> getQuizzesByCategory(Category category) {
        return quizRepository.findByCategory(category);
    }
    public List<Quiz> getQuizzesByCategory2(Category category) {
        return quizRepository.findByCategoryId(category.getCid());
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return quizRepository.findByActive();
    }

    @Override
    public List<Quiz> getCategoryAndActiveQuiz(Category category) {
        return quizRepository.findByCategoryAndActive(category.getCid());
    }

}
