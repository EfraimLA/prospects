package org.acme.prospects.controllers;

import org.acme.prospects.models.Prospect;
import org.acme.prospects.services.ProspectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prospects")
public class ProspectController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProspectController.class);

    @Autowired
    ProspectRepository repository;

    @GetMapping
    public ResponseEntity<List<Prospect>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prospect> getById(@PathVariable final Long id) {
        var prospect = repository.findById(id);
        
        if (prospect.isPresent()) return new ResponseEntity<>(prospect.get(), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Prospect> create(@RequestBody @Valid Prospect prospect) {
        return new ResponseEntity<>(repository.save(prospect), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Modifying
    public ResponseEntity<Prospect> update(@PathVariable final Long id, @RequestBody @Valid Prospect prospect) {
        var query = repository.findById(id);
        if (query.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        var entity = query.get();

        // entity.setName(prospect.getName()); Cannot update this field
        // entity.setLastName(prospect.getLastName()); Cannot update this field
        entity.setBirthdate(prospect.getBirthdate());
        entity.setGender(prospect.getGender());
        entity.setNationality(prospect.getNationality());
        entity.setMaritalStatus(prospect.getMaritalStatus());
        entity.setGrade(prospect.getGrade());
        entity.setIncome(prospect.getIncome());
        entity.setIncomeValidation(prospect.getIncomeValidation());
        entity.setJobAntiquity(prospect.getJobAntiquity());
        entity.setCreditHistory(prospect.getCreditHistory());
        entity.setActualCredit(prospect.getActualCredit());

        return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable final Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
