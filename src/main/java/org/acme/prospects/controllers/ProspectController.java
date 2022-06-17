package org.acme.prospects.controllers;

import org.acme.prospects.models.Prospect;
import org.acme.prospects.services.ProspectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProspectController {

    ProspectRepository repository;

    @GetMapping("/")
    public List<Prospect> index() {
        return repository.findAll();
    }

}
