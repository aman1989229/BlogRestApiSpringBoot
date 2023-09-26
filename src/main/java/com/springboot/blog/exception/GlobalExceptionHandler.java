package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler {
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
}
