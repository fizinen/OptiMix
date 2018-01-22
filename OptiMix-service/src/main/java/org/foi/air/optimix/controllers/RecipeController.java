/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.Recipe;
import org.foi.air.optimix.repositories.RecipeRepository;
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
@RequestMapping(value = "/recipe")
public class RecipeController {
    
    RecipeRepository recipeRepository;
    
    @Autowired
    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    
     /**
     * gets all recipes from database
     * @return all groups in json format with HTTP 200
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe>> retrieveAll() {
        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "GET on /recipe -- retrieving full list of recipes");
        return new ResponseEntity(this.recipeRepository.findAll(), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<Recipe>> retrieveRecipes() {
        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "POST on /recipe/all -- retrieving full list of recipes");
        return new ResponseEntity(this.recipeRepository.findAll(), HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/addrecipe", method = RequestMethod.POST)
    public ResponseEntity<Recipe> addrecipe(@RequestBody Recipe recipe) {

        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "POST on /recipe/addrecipe -- " + recipe.toString());

        Recipe signed = this.recipeRepository.save(recipe);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
    
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Recipe> retrieveById(@RequestParam long id) {
        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "GET on /recipe" + id + " -- ");
        Recipe found = this.recipeRepository.findByIdRecipe(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public ResponseEntity<Recipe> retrieveRecipeById(@RequestParam long id) {
        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "POST on /recipe" + id + " -- ");
        Recipe found = this.recipeRepository.findByIdRecipe(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Recipe recipe) {
        Logger.getLogger("RecipeController.java").log(Level.INFO,
                "PUT on /recipe" + id + " -- " + recipe.toString());
        
        Recipe signed = this.recipeRepository.findByIdRecipe(id);
        if(signed != null) {
            this.recipeRepository.save(recipe);
            Logger.getLogger("RecipeController.java").log(Level.INFO,
                    "Update successful for " + recipe.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("RecipeController.java").log(Level.WARNING,
                    "No recipe found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
}
