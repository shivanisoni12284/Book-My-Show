package com.example.bookmyshow.execption;

public class BookingCancelledException extends RuntimeException{

    public BookingCancelledException(String message){
        super(message);
    }
}
