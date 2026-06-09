package com.example.bookmyshow.execption;

public class ShowSeatNotFoundException extends RuntimeException{

    public ShowSeatNotFoundException(String message){

        super(message);
    }
}
