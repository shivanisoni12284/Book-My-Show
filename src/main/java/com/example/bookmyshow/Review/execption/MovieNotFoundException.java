package com.example.bookmyshow.Review.execption;


public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String message){
        super(message);
    }
}
