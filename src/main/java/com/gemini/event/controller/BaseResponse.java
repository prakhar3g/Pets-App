package com.gemini.event.controller;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    String message;
    HttpStatus status;
    Object object;

    BaseResponse(String message, HttpStatus status, Object object) {
        this.message = message;
        this.status = status;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

