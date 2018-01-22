/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.air.optimix.model.Calculation;
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
@RequestMapping(value = "calculation")
public class CalculationController {

    CalculationRepository calculationRepository;

    @Autowired
    public CalculationController(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

     /**
     * gets all calculations from database
     * @return all groups in json format with HTTP 200
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Calculation>> retrieveAll() {
        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "GET on /calculation -- retrieving full list of calculations");
        return new ResponseEntity(this.calculationRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<List<Calculation>> retrieveCalculations() {
        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "POST on /calculation/all -- retrieving full list of calculations");
        return new ResponseEntity(this.calculationRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addcalculation", method = RequestMethod.POST)
    public ResponseEntity<Calculation> addcalculation(@RequestBody Calculation calculation) {

        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "POST on /calculation/addcalculation -- " + calculation.toString());

        Calculation signed = this.calculationRepository.save(calculation);
        return (signed != null) ? new ResponseEntity(signed, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Calculation> retrieveById(@RequestParam long id) {
        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "GET on /calculation/" + id + " -- ");
        Calculation found = this.calculationRepository.findByIdCalculation(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Calculation> retrieveCalculationById(@RequestParam long id) {
        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "POST on /calculation/" + id + " -- ");
        Calculation found = this.calculationRepository.findByIdCalculation(id);

        return (found != null) ? new ResponseEntity(found, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Calculation calculation) {
        Logger.getLogger("CalculationController.java").log(Level.INFO,
                "PUT on /calculation/" + id + " -- " + calculation.toString());

        Calculation signed = this.calculationRepository.findByIdCalculation(id);
        if (signed != null) {
            this.calculationRepository.save(calculation);
            Logger.getLogger("CalculationController.java").log(Level.INFO,
                    "Update successful for " + calculation.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("CalculationController.java").log(Level.WARNING,
                    "No raw found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
