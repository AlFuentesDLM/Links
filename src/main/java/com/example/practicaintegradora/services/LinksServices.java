package com.example.practicaintegradora.services;

import com.example.practicaintegradora.dtos.request.LinkDtoRequest;
import com.example.practicaintegradora.dtos.response.LinkDto;
import com.example.practicaintegradora.dtos.response.MetricDto;
import com.example.practicaintegradora.exceptions.LinkNotValidException;
import com.example.practicaintegradora.exceptions.NoPasswordWasGiven;
import com.example.practicaintegradora.exceptions.UnauthorizedException;
import com.example.practicaintegradora.repository.LinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LinksServices {
    private final LinkRepository linkRepository;

    public LinksServices(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkDto addNewLink(LinkDtoRequest link) {
        var linkDto = linkRepository.addNew(link);
        var isValid = checkIfValid(linkDto);
        if (!isValid) {
            linkDto.setValid(false);
            linkRepository.update(linkDto);
        }
        return linkDto;
    }


    private boolean checkIfValid(LinkDto link) {
        boolean result;
        try {
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                    URI.create(link.getUrl()))
                    .header("accept", "application/json")
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    public void addCounter(LinkDto link) {
       link.setCounter(link.getCounter()+1);
       linkRepository.update(link);
    }

    public LinkDto invalidateUrl(String uuid) {
        var link = linkRepository.getById(uuid);
        link.setValid(false);
        linkRepository.update(link);
        return link;
    }

    public LinkDto getRedirect(String uuid,String password){
        var link = linkRepository.getById(uuid);
        if(link.isValid()){
            if (link.getPassword().equals(password)){
                return link;
            }else{
                throw new UnauthorizedException();
            }

        }else{
            throw new LinkNotValidException("Link is not valid",link);
        }
    }
    public LinkDto getRedirect(String uuid){
        var link = linkRepository.getById(uuid);
        if(link.isValid()){
            if (link.getPassword().equals("") || link.getPassword() == null){
                return link;
            }else {
                throw new NoPasswordWasGiven();
            }
        }else{
            throw new LinkNotValidException("Link is not valid",link);
        }
    }

    public List<LinkDto> getAllLinks(){
        return linkRepository.getAll();
    }

    public MetricDto getMetrics(String uuid){
        var link = linkRepository.getById(uuid);
        return new MetricDto(link.getUrl(),link.getName(),link.getCounter());
    }
}
