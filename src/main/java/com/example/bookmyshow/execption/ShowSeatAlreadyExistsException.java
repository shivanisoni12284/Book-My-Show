package com.example.bookmyshow.execption;

public class ShowSeatAlreadyExistsException extends  RuntimeException{

    public ShowSeatAlreadyExistsException(String message){
        super(message);
    }
}
