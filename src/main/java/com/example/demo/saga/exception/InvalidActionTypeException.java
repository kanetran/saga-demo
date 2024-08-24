package com.example.demo.saga.exception;

public class InvalidActionTypeException extends RuntimeException {

    // Default constructor
    public InvalidActionTypeException() {
        super();
    }

    // Constructor with a message
    public InvalidActionTypeException(String message) {
        super(message);
    }

    // Constructor with a message and a cause
    public InvalidActionTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public InvalidActionTypeException(Throwable cause) {
        super(cause);
    }
}
