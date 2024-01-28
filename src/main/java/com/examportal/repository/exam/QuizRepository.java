package com.examportal.repository.exam;

import com.examportal.model.exam.Category;
import com.examportal.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByCategory(Category category);

    @Query("SELECT q FROM quizzes q INNER JOIN q.category c WHERE c.cid = :cid")
    List<Quiz> findByCategoryId(@Param("cid") Long cid);

    @Query("SELECT q FROM quizzes q WHERE q.active = true")
    List<Quiz> findByActive();

    @Query("SELECT q FROM quizzes q INNER JOIN q.category c WHERE c.cid =:cid AND q.active = true")
    List<Quiz> findByCategoryAndActive(@Param("cid")Long cid);
}
