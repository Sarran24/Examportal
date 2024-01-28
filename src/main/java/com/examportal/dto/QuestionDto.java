package com.examportal.dto;

import com.examportal.model.exam.Question;
import com.examportal.model.exam.Quiz;

public class QuestionDto {
    private Long quesId;
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Quiz quiz;

    private String givenAnswer;

    public QuestionDto(Question q) {
        this.quesId = q.getQuesId();
        this.content = q.getContent();
        this.image = q.getImage();
        this.option1 = q.getOption1();
        this.option2 = q.getOption2();
        this.option3 = q.getOption3();
        this.option4 = q.getOption4();
        this.quiz = q.getQuiz();
        this.givenAnswer = q.getGivenAnswer();
    }


    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
    // getters and setters
}

