package com.example.practicaintegradora.exceptions;

public class NoPasswordWasGiven extends RuntimeException {
    public final String ERROR = "You need to give a password for this request";

    public NoPasswordWasGiven() {
    }
}
