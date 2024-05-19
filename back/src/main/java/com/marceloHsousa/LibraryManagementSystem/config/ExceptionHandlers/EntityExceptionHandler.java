package com.marceloHsousa.LibraryManagementSystem.config.ExceptionHandlers;

import com.marceloHsousa.LibraryManagementSystem.entities.book.exceptions.BookNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.entities.user.exceptions.UserNotFoundException;
import com.marceloHsousa.LibraryManagementSystem.resources.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> handleUserNotFound(UserNotFoundException e, HttpServletRequest request){
        StandardError error=new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "User not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<StandardError> handleBookNotFound(BookNotFoundException e, HttpServletRequest request){
        StandardError error=new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Book not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
