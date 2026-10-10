package com.ddd.event_ticketing_platform.sharedkernel.exception.handler;

import com.ddd.event_ticketing_platform.sharedkernel.dto.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order
public class RuntimeExceptionControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RuntimeExceptionControllerAdvice.class);
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse runtimeException(RuntimeException exception) {
        log.error("Runtime exception has occurred", exception);

        return new ErrorResponse(SOMETHING_WENT_WRONG);
    }

}
