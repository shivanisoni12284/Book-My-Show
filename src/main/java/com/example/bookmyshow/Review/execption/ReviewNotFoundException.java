package com.example.bookmyshow.Review.execption;


public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message){
        super(message);
    }
}
