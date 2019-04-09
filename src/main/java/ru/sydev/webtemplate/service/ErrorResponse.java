package ru.sydev.webtemplate.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ErrorResponse extends HttpStatusCodeException {
    public ErrorResponse(HttpStatus statusCode) {
        super(statusCode);
    }

    public ErrorResponse(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
