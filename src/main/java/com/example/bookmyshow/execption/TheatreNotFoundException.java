package com.example.bookmyshow.execption;

public class TheatreNotFoundException extends  RuntimeException{

    public TheatreNotFoundException(String message){
        super(message);
    }
}
