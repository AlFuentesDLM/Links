package com.example.practicaintegradora.repository;

import com.example.practicaintegradora.exceptions.LinkDoesntFoundException;

import java.util.List;

public interface CRUD<Response,Request> {
    List<Response> getAll();
    Response getById(String id) throws LinkDoesntFoundException;
    Response addNew(Request obj);
    boolean removeOneById(String id);
    Response update(Response ojb);
}
