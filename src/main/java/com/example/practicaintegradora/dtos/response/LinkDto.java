package com.example.practicaintegradora.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LinkDto {
    private String id;
    private String name;
    private String url;
    private boolean isValid;
    private int counter;

    public LinkDto(String id, String name, String url, boolean isValid) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.isValid = isValid;
        this.counter = 0;
    }
}
