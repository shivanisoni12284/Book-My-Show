package com.example.bookmyshow.execption;

public class SeatAlreadyExistsException extends RuntimeException{
    public SeatAlreadyExistsException(String message){
        super(message);
    }
}
