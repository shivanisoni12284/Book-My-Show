package com.example.bookmyshow.execption;


public class NotificationNotFoundException extends RuntimeException{

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
