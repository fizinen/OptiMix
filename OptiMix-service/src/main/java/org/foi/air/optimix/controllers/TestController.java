/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gloria Babić
 */
@RestController
public class TestController {
    
    /*private CredentialsRepository credentialsRepository;

    @Autowired
    public TestController(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }
    
    @GetMapping(path = "/test")
    public ResponseEntity<List<Credentials>> test() {
        Credentials c = new Credentials();
        c.setUsername("pperic");
        c.setPassword("l0zink4");
        credentialsRepository.save(c);
        return new ResponseEntity<>(credentialsRepository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping(path = "/test")
    public void addCredentials(@RequestBody Credentials c) {
        // provjere
        credentialsRepository.save(c);
    }
    
    @GetMapping(path = "/test/{var}")
    public ResponseEntity<Credentials> something(@PathVariable String var) {
        Credentials c = credentialsRepository.findByUsername(var);
        return c != null
                ? new ResponseEntity<>(c, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }*/
}
