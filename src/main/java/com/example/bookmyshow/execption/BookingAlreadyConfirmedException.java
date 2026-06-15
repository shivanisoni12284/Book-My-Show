package com.example.bookmyshow.execption;

public class BookingAlreadyConfirmedException extends RuntimeException{

    public BookingAlreadyConfirmedException(String message){
        super(message);

    }
}
