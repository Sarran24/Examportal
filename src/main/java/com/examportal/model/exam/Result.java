package com.examportal.model.exam;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Result {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double marksGot;
    private int correctMarks;
    private int attempted;

    private String  userid;

    public Result() {
    }

    public Result(Long id, double marksGot, int correctMarks, int attempted, String userid) {
        this.id = id;
        this.marksGot = marksGot;
        this.correctMarks = correctMarks;
        this.attempted = attempted;
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMarksGot() {
        return marksGot;
    }

    public void setMarksGot(double marksGot) {
        this.marksGot = marksGot;
    }

    public int getCorrectMarks() {
        return correctMarks;
    }

    public void setCorrectMarks(int correctMarks) {
        this.correctMarks = correctMarks;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
