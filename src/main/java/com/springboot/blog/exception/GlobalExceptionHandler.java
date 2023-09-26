package com.springboot.blog.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> HandleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        String string = new Date()+", "+exception.getMessage()+", "+webRequest.getDescription(false);
        return new ResponseEntity<>(string, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<String> HandleBlogAPIException(BlogAPIException exception, WebRequest webRequest){
        String string = new Date()+", "+exception.getMessage()+", "+webRequest.getDescription(false);
        return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);

    }
    //handle global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandleGlobalException(Exception exception, WebRequest webRequest){
        String string = new Date()+", "+exception.getMessage()+", "+webRequest.getDescription(false);
        return new ResponseEntity<>(string, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String > errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
           String fieldName = ((FieldError)error).getField();
           String message = error.getDefaultMessage();
           errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
}
