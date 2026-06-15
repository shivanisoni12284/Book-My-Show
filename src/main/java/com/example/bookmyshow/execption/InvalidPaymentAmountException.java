package com.example.bookmyshow.execption;

public class InvalidPaymentAmountException extends RuntimeException {

    public InvalidPaymentAmountException(String message){
        super(message);
    }
}
