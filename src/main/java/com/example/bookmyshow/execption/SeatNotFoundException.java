package com.example.bookmyshow.execption;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException(String message){
        super(message);
    }
}
