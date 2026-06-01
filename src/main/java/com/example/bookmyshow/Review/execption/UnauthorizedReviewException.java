package com.example.bookmyshow.Review.execption;

public class UnauthorizedReviewException extends RuntimeException{

    public UnauthorizedReviewException(String message){
        super(message);
    }
}
