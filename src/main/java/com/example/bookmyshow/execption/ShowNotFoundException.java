package com.example.bookmyshow.execption;


public class ShowNotFoundException extends RuntimeException{

    public ShowNotFoundException(Long showId){
        super("show not found with id :"+ showId);
    }
}
