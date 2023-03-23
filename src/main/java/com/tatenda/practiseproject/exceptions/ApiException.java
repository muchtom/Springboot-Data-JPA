package com.tatenda.practiseproject.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
//    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public String getMessage() {
        return message;
    }

//    public HttpStatus getHttpStatus() {
//        return httpStatus;
//    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public ApiException(String message,ZonedDateTime timeStamp) {
        this.message = message;
//        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }
}
