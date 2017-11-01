/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.Person;
import org.foi.air.optimix.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gloria Babić
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> retrieveAll() {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "GET on /person -- retrieving full list of users");
        return new ResponseEntity(this.personRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Person> login(@RequestBody Person person) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "POST on /person/login -- " + person.toString());
        String username = person.getUsername();
        String password = person.getPassword();

        Person found = this.personRepository.findByUsername(username);
        
       Logger.getLogger("ima li te? Personnn?").log(Level.INFO,
                "đes " + found.toString());
        
        return (found != null && found.getPassword().equals(password))
                ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public ResponseEntity<Person> adduser(@RequestBody Person person) {

        Logger.getLogger("PersonController.java").log(Level.INFO,
                "POST on /person/adduser -- " + person.toString());

        Person signed = this.personRepository.save(person);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> retrieveById(@RequestParam long id) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "GET on /person/" + id + " -- ");
        Person found = this.personRepository.findByIdPerson(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Person person) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "PUT on /person/" + id + " -- " + person.toString());
        
        Person signed = this.personRepository.findByIdPerson(id);
        if(signed != null) {
            this.personRepository.save(person);
            Logger.getLogger("PersonController.java").log(Level.INFO,
                    "Update successful for " + person.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("PersonController.java").log(Level.WARNING,
                    "No user found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
}

}
