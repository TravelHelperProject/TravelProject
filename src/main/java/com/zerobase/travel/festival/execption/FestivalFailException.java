package com.zerobase.travel.festival.execption;

public class FestivalFailException  extends RuntimeException{
    public FestivalFailException() {
        super();
    }
    public FestivalFailException(String message) {
        super(message);
    }
    public FestivalFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FestivalFailException(Throwable cause) {
        super(cause);
    }

}
