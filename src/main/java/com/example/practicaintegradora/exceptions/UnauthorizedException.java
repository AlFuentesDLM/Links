package com.example.practicaintegradora.exceptions;

public class UnauthorizedException extends RuntimeException{
    public final String ERROR = "Unauthorized, you need a correct password to access to this link";

    public UnauthorizedException() {
    }
}
