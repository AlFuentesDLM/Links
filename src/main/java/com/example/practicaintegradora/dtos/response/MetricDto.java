package com.example.practicaintegradora.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetricDto {
    private String url;
    private String name;
    private int visits;
}
