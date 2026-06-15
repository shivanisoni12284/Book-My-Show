package com.example.bookmyshow.execption;

public class PaymentAlreadyConfirmedException extends RuntimeException{

    public PaymentAlreadyConfirmedException(String message){
        super(message);
    }
}
