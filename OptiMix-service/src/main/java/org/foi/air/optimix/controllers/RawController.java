/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.Raw;
import org.foi.air.optimix.repositories.RawRepository;
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
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "/raw")
public class RawController {
    
    RawRepository rawRepository;
    
    @Autowired
    public RawController (RawRepository rawRepository){
        this.rawRepository = rawRepository;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Raw>> retrieveAll() {
        Logger.getLogger("RawController.java").log(Level.INFO,
                "GET on /raw -- retrieving full list of materials");
        return new ResponseEntity(this.rawRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<Raw>> retrieveUsers() {
        Logger.getLogger("RawController.java").log(Level.INFO,
                "POST on /raw/all -- retrieving full list of materials");
        return new ResponseEntity(this.rawRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addraw", method = RequestMethod.POST)
    public ResponseEntity<Raw> addraw(@RequestBody Raw raw) {

        Logger.getLogger("RawController.java").log(Level.INFO,
                "POST on /raw/addraw -- " + raw.toString());

        Raw signed = this.rawRepository.save(raw);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Raw> retrieveById(@RequestParam long id) {
        Logger.getLogger("RawController.java").log(Level.INFO,
                "GET on /raw/" + id + " -- ");
        Raw found = this.rawRepository.findByIdRaw(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Raw raw) {
        Logger.getLogger("RawController.java").log(Level.INFO,
                "PUT on /raw/" + id + " -- " + raw.toString());
        
        Raw signed = this.rawRepository.findByIdRaw(id);
        if(signed != null) {
            this.rawRepository.save(raw);
            Logger.getLogger("RawController.java").log(Level.INFO,
                    "Update successful for " + raw.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("RawController.java").log(Level.WARNING,
                    "No raw found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
}
    
}
