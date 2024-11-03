package com.srsvmj.employee_service.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String description;
    private String errorCode;

    public ErrorDetails(LocalDateTime localDateTime, String message, String description, String errorCode) {
        this.localDateTime = localDateTime;
        this.message = message;
        this.description = description;
        this.errorCode = errorCode;
    }
}
