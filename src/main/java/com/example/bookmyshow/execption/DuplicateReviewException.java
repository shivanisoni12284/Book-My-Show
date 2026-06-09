package com.example.bookmyshow.execption;

public class DuplicateReviewException extends RuntimeException{


    public DuplicateReviewException(String message){
        super(message);
    }
}
