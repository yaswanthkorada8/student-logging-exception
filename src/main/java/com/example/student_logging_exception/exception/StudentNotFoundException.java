package com.example.student_logging_exception.exception;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }
}