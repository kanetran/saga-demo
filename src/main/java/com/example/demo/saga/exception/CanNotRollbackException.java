package com.example.demo.saga.exception;

public class CanNotRollbackException extends RuntimeException {

    // Default constructor
    public CanNotRollbackException() {
        super();
    }

    // Constructor with a message
    public CanNotRollbackException(String message) {
        super(message);
    }

    // Constructor with a message and a cause
    public CanNotRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public CanNotRollbackException(Throwable cause) {
        super(cause);
    }
}
