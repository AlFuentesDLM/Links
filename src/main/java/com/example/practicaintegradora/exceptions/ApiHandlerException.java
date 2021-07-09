package com.example.practicaintegradora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@ControllerAdvice(annotations = RestController.class)
public class ApiHandlerException {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handler(LinkNotValidException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleLinkDoesntFound(LinkDoesntFoundException ex){
        return  new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleCollectionIsZero(CollectionSizeIsZeroException ex){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public  ErrorMessage handleValidation(ConstraintViolationException ex){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleUnauthorizedException(UnauthorizedException ex){
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), ex.ERROR);
    }


}