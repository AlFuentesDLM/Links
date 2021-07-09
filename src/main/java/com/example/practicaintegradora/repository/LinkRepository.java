package com.example.practicaintegradora.repository;

import com.example.practicaintegradora.dtos.request.LinkDtoRequest;
import com.example.practicaintegradora.dtos.response.LinkDto;
import com.example.practicaintegradora.exceptions.CollectionSizeIsZeroException;
import com.example.practicaintegradora.exceptions.LinkDoesntFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class LinkRepository implements CRUD<LinkDto,LinkDtoRequest> {
    public List<LinkDto> links;

    public LinkRepository() {
        this.links = new ArrayList<>();
    }


    @Override
    public List<LinkDto> getAll() {
        if (links.size()>0){
            return links;
        }else{
            throw new CollectionSizeIsZeroException();
        }
    }

    @Override
    public LinkDto getById(String id) {
        var link = links.stream().filter(linkDto -> linkDto.getId().equals(id)).findFirst();
        if (link.isPresent()){
            return link.get();
        }else{
            throw new LinkDoesntFoundException();
        }
    }

    @Override
    public LinkDto addNew(LinkDtoRequest obj) {
        var uuid = UUID.randomUUID();
        LinkDto link;
        if (obj.getPassword() == null){
            link = new LinkDto(uuid.toString(),obj.getName(), obj.getUrl(), true);
        }else{
            link = new LinkDto(uuid.toString(),obj.getName(), obj.getUrl(), true,obj.getPassword());
        }
        links.add(link);
        return link;
    }

    @Override
    public boolean removeOneById(String id) {
        return false;
    }

    @Override
    public LinkDto update(LinkDto link) {
        try {
            var index = links.indexOf(link);
            links.set(index,link);
            return link;
        }catch (NullPointerException e){
            throw new LinkDoesntFoundException();
        }
    }
}
