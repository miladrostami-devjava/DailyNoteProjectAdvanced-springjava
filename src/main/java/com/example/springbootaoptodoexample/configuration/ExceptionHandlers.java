package com.example.springbootaoptodoexample.configuration;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {


    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(
            final ServletRequestBindingException bindingException,
            HttpServletRequest httpServletRequest){
        log.error("Missing parameter",bindingException);
        return new ErrorResponse("MISSING_PARAMETER",
                bindingException.getMessage(),
                httpServletRequest.getRequestURI(),
                "Required parameter is missing",
                LocalDateTime.now());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(final IllegalArgumentException illegalArgumentException,
                                HttpServletRequest httpServletRequest){
        log.debug("Illegal argument provided debugging {}",illegalArgumentException.getMessage());
        log.error("Missing parameter",illegalArgumentException);
        return new ErrorResponse("ILLEGAL_ARGUMENT",
                illegalArgumentException.getMessage(),
                httpServletRequest.getRequestURI(),
                "Illegal argument provided ",
                LocalDateTime.now());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handle(final ForbiddenException forbiddenException,
                                HttpServletRequest httpServletRequest){
        log.error("Missing parameter",forbiddenException);
        log.warn("forbidden exception warning {}",forbiddenException.getMessage());
        return new ErrorResponse("FORBIDEN_ACCESS",
                forbiddenException.getMessage(),
                httpServletRequest.getRequestURI(),
                "Access to this resource is forbidden",
                LocalDateTime.now());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handle(final Throwable throwable,
                                HttpServletRequest httpServletRequest){
        log.error("unexpected error",throwable);
        return new ErrorResponse("INTERNAL_SERVER_ERROR",
                throwable.getMessage(),
                httpServletRequest.getRequestURI(),
                "An unexpected error occurred",
                LocalDateTime.now());
    }

// helper method added to class

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handle(final NullPointerException nullPointerException
    ,HttpServletRequest httpServletRequest){
        log.info("Null Pointer Exception",nullPointerException);
        log.trace("null pointer exception trace {}",nullPointerException.getMessage());
return createErrorResponseNullPointerExceptionHandler(
        "NULL_POINTER _EXCEPTION ",
        nullPointerException.getMessage(),
        "/me/null/path",
        "Details about null pointer exception"
);
    }
    private ErrorResponse createErrorResponseNullPointerExceptionHandler(String code,String message,String path,String details ){
        return new ErrorResponse(code,message,path,details,LocalDateTime.now());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handle(final ResourceNotFoundException resourceNotFoundException
            ,HttpServletRequest httpServletRequest){
        log.info("Resource Not Found",resourceNotFoundException);
        return createErrorResponseResourceNotFoundExceptionHandler(
                "NULL_POINTER _EXCEPTION ",
                resourceNotFoundException.getMessage(),
                "details for resource path",
                "/me/resource/path"
        );
    }

    private ErrorResponse createErrorResponseResourceNotFoundExceptionHandler( String code, String message,String details, String path) {
        return new ErrorResponse(code,message,path,details,LocalDateTime.now());
    }








    public record ErrorResponse(String code,String message,String path,String details,LocalDateTime timestamp){
    }


    // other method solution


/*
@Data
    public static class ErrorResponse {
        private final String code;
        private final String message;


       // Add new parameters:

//details: Provides more details about the error.
//path: records the URL path of the request.
//timestamp: records the time when the error occurred.
    private final String path;
    private final String details;
    private final LocalDateTime timestamp;

    public ErrorResponse(String code, String message, String path, String details, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.path = path;
        this.details = details;
        this.timestamp = timestamp;
    }
}*/


    //other method for implementation
  /*  public record ErrorResponse(String code, String message) {
    }*/
}
