/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.RecipeRaws;
import org.foi.air.optimix.repositories.RecipeRawsRepository;
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
@RequestMapping(value = "/reciperaws")
public class RecipeRawsController {
    
    RecipeRawsRepository recipeRawsRepository;
    
    @Autowired
    public RecipeRawsController (RecipeRawsRepository recipeRawsRepository) {
        this.recipeRawsRepository = recipeRawsRepository;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<RecipeRaws>> retrieveAll() {
        Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                "GET on /reciperaws -- retrieving full list of raws of recipe");
        return new ResponseEntity(this.recipeRawsRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<RecipeRaws>> retrieveRecipeRaws() {
        Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                "POST on /reciperaws/all -- retrieving full list of raws of recipe");
        return new ResponseEntity(this.recipeRawsRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addreciperaws", method = RequestMethod.POST)
    public ResponseEntity<RecipeRaws> addreciperaws (@RequestBody RecipeRaws recipeRaws) {

        Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                "POST on /reciperaws/addreciperaws -- " + recipeRaws.toString());

        RecipeRaws signed = this.recipeRawsRepository.save(recipeRaws);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RecipeRaws> retrieveById(@RequestParam long id) {
        Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                "GET on /reciperaws/" + id + " -- ");
        RecipeRaws found = this.recipeRawsRepository.findByIdRecipeRaws(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody RecipeRaws recipeRaws) {
        Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                "PUT on /analysis/" + id + " -- " + recipeRaws.toString());
        
        RecipeRaws signed = this.recipeRawsRepository.findByIdRecipeRaws(id);
        if(signed != null) {
            this.recipeRawsRepository.save(recipeRaws);
            Logger.getLogger("RecipeRawsController.java").log(Level.INFO,
                    "Update successful for " + recipeRaws.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("RecipeRawsController.java").log(Level.WARNING,
                    "No recipeRaws found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
}
}
