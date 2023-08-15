package com.zerobase.travel.board.exception;

public class BoardFailException extends RuntimeException{

    public BoardFailException() {
        super();
    }
    public BoardFailException(String message) {
        super(message);
    }
    public BoardFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardFailException(Throwable cause) {
        super(cause);
    }
}
