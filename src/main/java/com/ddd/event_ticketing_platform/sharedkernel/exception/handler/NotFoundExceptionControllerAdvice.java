package com.ddd.event_ticketing_platform.sharedkernel.exception.handler;

import com.ddd.event_ticketing_platform.sharedkernel.dto.response.ErrorResponse;
import com.ddd.event_ticketing_platform.sharedkernel.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotFoundExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(NotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

}
