package com.example.practicaintegradora.controllers;

import com.example.practicaintegradora.dtos.request.LinkDtoRequest;
import com.example.practicaintegradora.dtos.response.LinkDto;
import com.example.practicaintegradora.dtos.response.MetricDto;
import com.example.practicaintegradora.services.LinksServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class LinkController {
    private final LinksServices linksServices;

    public LinkController(LinksServices linksServices) {
        this.linksServices = linksServices;
    }

    @PostMapping(path = "/link")
    public ResponseEntity<LinkDto> addLink(@RequestBody LinkDtoRequest link) {
        return new ResponseEntity<>(linksServices.addNewLink(link), HttpStatus.OK);
    }

    @GetMapping(path = "/link")
    public ResponseEntity<List<LinkDto>> getAllLinks() {
        return new ResponseEntity<>(linksServices.getAllLinks(), HttpStatus.OK);
    }

    @GetMapping("/link/{linkId}")
    public RedirectView getAndRedirect(@NotNull @Size(min = 36, max = 36) @PathVariable String linkId,
                                       @RequestParam(name = "password", defaultValue = "") String password) {
        LinkDto link;
        System.out.println(password);
        if (password.equals("")) {
            link = linksServices.getRedirect(linkId);
        } else {
            link = linksServices.getRedirect(linkId, password);
        }
        linksServices.addCounter(link);
        return new RedirectView(link.getUrl());
    }

    @GetMapping(path = "/metrics/{linkId}")
    public ResponseEntity<MetricDto> getMetrics(@NotNull @Size(min = 36, max = 36) @PathVariable String linkId) {
        return new ResponseEntity<>(linksServices.getMetrics(linkId), HttpStatus.OK);
    }

    @PatchMapping(path = "/invalidate/{linkId}")
    public ResponseEntity<LinkDto> invalidateLink(@NotNull @Size(min = 36, max = 36) @PathVariable String linkId) {
        return new ResponseEntity<>(linksServices.invalidateUrl(linkId), HttpStatus.OK);
    }
}
