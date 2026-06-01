package com.example.bookmyshow.Review.execption;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { //This class catches exceptions from all controllers.

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFound(MovieNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<String> handleReviewNotFound(ReviewNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(DuplicateReviewException.class)
    public ResponseEntity<String> handleDuplicateReviewException(DuplicateReviewException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

    }

    @ExceptionHandler(UnauthorizedReviewException.class)
    public ResponseEntity<String> handleUnauthorizedReviewException(UnauthorizedReviewException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());

    }





}