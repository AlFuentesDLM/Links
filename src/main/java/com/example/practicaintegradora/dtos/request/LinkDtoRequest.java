package com.example.practicaintegradora.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class LinkDtoRequest {
    @NotNull(message = "url cant be null")
    private String url;
    @NotNull(message = "name cant be null")
    private String name;
    private String password;

    public LinkDtoRequest() {
    }

    public LinkDtoRequest(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public LinkDtoRequest(String url, String name, String password) {
        this.url = url;
        this.name = name;
        this.password = password;
    }
}
