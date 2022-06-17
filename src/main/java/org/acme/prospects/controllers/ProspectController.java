package org.acme.prospects.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProspectController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }
}
