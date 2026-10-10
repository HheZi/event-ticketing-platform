package com.ddd.event_ticketing_platform.sharedkernel.dto.response;

import jakarta.annotation.Nullable;

public class ErrorResponse {

    private String fieldName;

    private String message;

    public ErrorResponse(@Nullable String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this(null, message);
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
