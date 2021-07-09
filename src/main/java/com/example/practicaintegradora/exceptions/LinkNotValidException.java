package com.example.practicaintegradora.exceptions;

import com.example.practicaintegradora.dtos.response.LinkDto;

public class LinkNotValidException extends RuntimeException{
    private LinkDto link;
    public LinkNotValidException(String message, LinkDto linkDto) {
        super(message);
        this.link = linkDto;
    }

    @Override
    public String getMessage() {
        return "the following link is invalidated, by the user or by the system: "+link.getUrl();
    }
}
