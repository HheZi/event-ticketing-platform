package com.ddd.event_ticketing_platform.sharedkernel.exception.handler;

import com.ddd.event_ticketing_platform.sharedkernel.dto.response.ErrorResponse;
import com.ddd.event_ticketing_platform.sharedkernel.exception.InvariantException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InvariantExceptionControllerAdvice {

    @ExceptionHandler(InvariantException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErrorResponse invariant(InvariantException exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
