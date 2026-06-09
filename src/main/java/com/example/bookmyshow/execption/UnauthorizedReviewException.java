package com.example.bookmyshow.execption;

public class UnauthorizedReviewException extends RuntimeException{

    public UnauthorizedReviewException(String message){
        super(message);
    }
}
