package com.examportal.exception;

public class UserNotExistException  extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotExistException(String msg) {
        super(msg);
    }

}
