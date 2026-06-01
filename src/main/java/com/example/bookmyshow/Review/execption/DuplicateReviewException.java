package com.example.bookmyshow.Review.execption;

public class DuplicateReviewException extends RuntimeException{


    public DuplicateReviewException(String message){
        super(message);
    }
}
