package com.examportal.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.*;


@Entity(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qid;
    private String title;
    @Column(length = 5000)
    private String description;
    private String maxMarks;
    private String noOfQuestion;

    private String username;
    private  boolean active =false;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    public Quiz() {
    }


    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(String noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "qid=" + qid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", maxMarks='" + maxMarks + '\'' +
                ", noOfQuestion='" + noOfQuestion + '\'' +
                ", active=" + active +
                ", category=" + category +
                ", questions=" + questions +
                '}';
    }
}
