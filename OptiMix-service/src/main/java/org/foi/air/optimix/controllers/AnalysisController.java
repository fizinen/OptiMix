/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.Analysis;
import org.foi.air.optimix.repositories.AnalysisRepository;
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
@RequestMapping(value = "/analysis")
public class AnalysisController {
    
    AnalysisRepository analysisRepository;
    
    @Autowired
    public AnalysisController (AnalysisRepository analysisRepository) {
        this.analysisRepository = analysisRepository;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Analysis>> retrieveAll() {
        Logger.getLogger("AnalysisController.java").log(Level.INFO,
                "GET on /analysis -- retrieving full list of analysis");
        return new ResponseEntity(this.analysisRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<Analysis>> retrieveUsers() {
        Logger.getLogger("AnalysisController.java").log(Level.INFO,
                "POST on /analysis/all -- retrieving full list of analysis");
        return new ResponseEntity(this.analysisRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addanalysis", method = RequestMethod.POST)
    public ResponseEntity<Analysis> addanalysis (@RequestBody Analysis analysis) {

        Logger.getLogger("AnalysisController.java").log(Level.INFO,
                "POST on /analysis/addanalysis -- " + analysis.toString());

        Analysis signed = this.analysisRepository.save(analysis);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Analysis> retrieveById(@RequestParam long id) {
        Logger.getLogger("AnalysisController.java").log(Level.INFO,
                "GET on /analysis/" + id + " -- ");
        Analysis found = this.analysisRepository.findByIdAnalysis(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Analysis analysis) {
        Logger.getLogger("AnalysisController.java").log(Level.INFO,
                "PUT on /analysis/" + id + " -- " + analysis.toString());
        
        Analysis signed = this.analysisRepository.findByIdAnalysis(id);
        if(signed != null) {
            this.analysisRepository.save(analysis);
            Logger.getLogger("AnalysisController.java").log(Level.INFO,
                    "Update successful for " + analysis.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("AnalysisController.java").log(Level.WARNING,
                    "No analysis found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
}
    
}
