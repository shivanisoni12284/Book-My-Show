package com.example.bookmyshow.execption;

import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(TheatreNotFoundException.class)
    public ResponseEntity<String> handleTheatreNotFoundException(TheatreNotFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());

    }

    @ExceptionHandler(ScreenNotFoundException.class)
    public ResponseEntity<String> handleScreenNotFoundException(ScreenNotFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleSeatNotFoundException(SeatNotFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(ShowNotFoundException.class)
    public ResponseEntity<String> handleShowNotFoundException(ShowNotFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(ShowSeatAlreadyExistsException.class)
    public ResponseEntity<String> handleShowSeatAlreadyExistsException(ShowSeatAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ShowSeatNotFoundException.class)
    public ResponseEntity<String> handleShowSeatNotFoundException(ShowSeatNotFoundException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(BookingAlreadyConfirmedException.class)
    public ResponseEntity<String> handleBookingAlreadyConfirmedException(BookingAlreadyConfirmedException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(BookingOwnerShipException.class)
    public ResponseEntity<String> handleBookingOwnerShipException(BookingOwnerShipException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(BookingCancelledException.class)
    public ResponseEntity<String> handleBookingCancelledException(BookingCancelledException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PaymentAlreadyConfirmedException.class)
    public ResponseEntity<String> handlePaymentAlreadyConfirmedException(PaymentAlreadyConfirmedException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPaymentAmountException.class)
    public ResponseEntity<String> handleInvalidPaymentAmountException(InvalidPaymentAmountException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<String> handleNotificationNotFoundException(NotificationNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



}