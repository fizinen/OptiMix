/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.CalculationAnalysis;
import org.foi.air.optimix.repositories.AnalysisRepository;
import org.foi.air.optimix.repositories.CalculationAnalysisRepository;
import org.foi.air.optimix.repositories.CalculationRepository;
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
@RequestMapping(value = "calculationanalysis")
public class CalculationAnalysisController {

    CalculationAnalysisRepository calculationAnalysisRepository;
    CalculationRepository calculationRepository;
    AnalysisRepository analysisRepository;

    @Autowired
    public CalculationAnalysisController(CalculationAnalysisRepository calculationAnalysisRepository, CalculationRepository calculationRepository, AnalysisRepository analysisRepository) {
        this.calculationAnalysisRepository = calculationAnalysisRepository;
        this.calculationRepository = calculationRepository;
        this.analysisRepository = analysisRepository;
    }

     /**
     * gets all calculationsAnalysises from database
     * @return all groups in json format with HTTP 200
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CalculationAnalysis>> retrieveAll() {
        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "GET on /calculationanalysis -- retrieving full list of calculations with analysis");
        return new ResponseEntity(this.calculationAnalysisRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<CalculationAnalysis>> retrieveCalculationAnalysis() {
        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "POST on /calculationanalysis/all -- retrieving full list of calculations with analysis");
        return new ResponseEntity(this.calculationAnalysisRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addcalculationanalysis", method = RequestMethod.POST)
    public ResponseEntity<CalculationAnalysis> addcalculationanalysis(@RequestBody CalculationAnalysis calculationAnalysis) {

        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "POST on /calculationanalysis/addcalculationanalysis -- " + calculationAnalysis.toString());

        CalculationAnalysis signed = this.calculationAnalysisRepository.save(calculationAnalysis);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CalculationAnalysis> retrieveById(@RequestParam long id) {
        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "GET on /calculationanalysis/" + id + " -- ");
        CalculationAnalysis found = this.calculationAnalysisRepository.findByIdCalculationAnalysis(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }
    
    
    @RequestMapping(value = "/calculation{calculationId}", method = RequestMethod.GET)
    public ResponseEntity<List<CalculationAnalysis>> retrieveAllForCalculations(@RequestParam("calculation_id") long calculationId) {
        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "GET on /calculationanalysis/calculation" + calculationId + " -- retrieving calculationanalysis list of specific calculation");
        
        return new ResponseEntity(this.calculationAnalysisRepository.findAllByCalculationId(calculationId), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody CalculationAnalysis calculationAnalysis) {
        Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                "PUT on /calculationanalysis/" + id + " -- " + calculationAnalysis.toString());

        CalculationAnalysis signed = this.calculationAnalysisRepository.findByIdCalculationAnalysis(id);
        if (signed != null) {
            this.calculationAnalysisRepository.save(calculationAnalysis);
            Logger.getLogger("CalculationAnalysisController.java").log(Level.INFO,
                    "Update successful for " + calculationAnalysis.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("CalculationAnalysisController.java").log(Level.WARNING,
                    "No recipeRaws found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
