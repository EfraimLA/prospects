package org.acme.prospects.controllers;

import org.acme.prospects.models.Prospect;
import org.acme.prospects.services.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prospects")
public class ProspectController {

    @Autowired
    ProspectRepository repository;

    @GetMapping
    public List<Prospect> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Prospect create(Prospect prospect) {
        return repository.save(prospect);
    }

    @PutMapping
    public Prospect update(Prospect prospect) {
        return repository.save(prospect);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        repository.deleteById(id);
    }

}
