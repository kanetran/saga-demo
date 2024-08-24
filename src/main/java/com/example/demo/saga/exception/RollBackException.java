package com.example.demo.saga.exception;

public class RollBackException extends RuntimeException {

    // Default constructor
    public RollBackException() {
        super();
    }

    // Constructor with a message
    public RollBackException(String message) {
        super(message);
    }

    // Constructor with a message and a cause
    public RollBackException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public RollBackException(Throwable cause) {
        super(cause);
    }
}
