package com.example.springbootaoptodoexample.configuration;

import com.example.springbootaoptodoexample.aspects.ForbiddenException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {


    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(final ServletRequestBindingException bindingException){
        log.error("Missing parameter",bindingException);
        return new ErrorResponse("MISSING_PARAMETER", bindingException.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(final IllegalArgumentException illegalArgumentException){
        log.error("Missing parameter",illegalArgumentException);
        return new ErrorResponse("ILLEGAL_ARGUMENT",illegalArgumentException.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handle(final ForbiddenException forbiddenException){
        log.error("Missing parameter",forbiddenException);
        return new ErrorResponse("FORBIDEN_ACCESS", forbiddenException.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(final Throwable throwable){
        log.error("unexpected error",throwable);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", throwable.getMessage());
    }





@Data
    public static class ErrorResponse {
        private final String code;
        private final String message;
    }
    //other method for implementation
  /*  public record ErrorResponse(String code, String message) {
    }*/
}
