package com.example.bookmyshow.execption;

public class ScreenNotFoundException extends RuntimeException{

    public ScreenNotFoundException(String message){
        super(message);
    }
}
