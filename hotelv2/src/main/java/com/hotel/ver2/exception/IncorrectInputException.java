package com.hotel.ver2.exception;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String msg, Throwable t) {
        super(msg, t);
    }

    public IncorrectInputException(String msg) {
        super(msg);
    }
}
