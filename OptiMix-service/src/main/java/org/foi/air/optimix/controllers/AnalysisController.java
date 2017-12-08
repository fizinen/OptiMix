/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
=======
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
>>>>>>> eed3de0e1773a7ac958aadcfb479337957649e03
import org.springframework.web.bind.annotation.RestController;

/**
 *
<<<<<<< HEAD
 * @author Gloria Babić
=======
 * @author Lenovo
>>>>>>> eed3de0e1773a7ac958aadcfb479337957649e03
 */
@RestController
@RequestMapping(value = "/analysis")
public class AnalysisController {
    
<<<<<<< HEAD
    
    
    AnalysisRepositiry teamRepository;
    PersonRepository personRepository;
    private SimpMessagingTemplate template;
    
    @Autowired
    public TeamController(TeamRepository groupRepository, PersonRepository personRepository, SimpMessagingTemplate template) {
        
        this.template = template;
        this.teamRepository = groupRepository;
        this.personRepository = personRepository;
    }
     
     /**
     * gets all groups from database
     * @return all groups in json format with HTTP 200
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> retrieveAll() {
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "GET on /team/ -- retrieving full list of groups");

        return new ResponseEntity(this.teamRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{idTeam}/leave/{idPerson}", method = RequestMethod.POST)
    public ResponseEntity removeFromTeam(@PathVariable long idPerson, @PathVariable long idTeam) {
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "Removing user "+ idPerson + " from team");
        
        Team found = this.teamRepository.findByIdTeam(idTeam);
        Person removePerson = this.personRepository.findByIdPerson(idPerson);
        
        if(removePerson == found.getCreator()){
            template.convertAndSend("/topic/team/"+ idTeam, "SOCKFIN" );
            found.setActive(0);
        }
            
        
        found.getMembers().remove(removePerson);
        
        return ((this.teamRepository.save(found)) != null)? new ResponseEntity(HttpStatus.OK):
                                                            new ResponseEntity(HttpStatus.BAD_REQUEST);
          
          
    }
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ResponseEntity<Team> createTeam(@RequestBody Team t){
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "POST on /team/create -- creating team " + t.toString());
        
        t.setActive(1);
        Team created = teamRepository.save(t);
        
        return (created != null)? new ResponseEntity(created,HttpStatus.OK):
                                  new ResponseEntity(HttpStatus.BAD_REQUEST);
           
       
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTeam (@PathVariable long id){
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "DELETE on /team/" + id);
        Team found = teamRepository.findByIdTeam(id);
        
        if(found != null){
            Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                    "Successfully found team " + found.toString());
            teamRepository.delete(found);
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "No team found for " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{idTeam}/person/{idPerson}", method=RequestMethod.POST)
    public ResponseEntity addPeopleToTeam (@PathVariable long idTeam,@PathVariable long idPerson){
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO, "POST on /team/" + idTeam 
        + "/person/" + idPerson);
        Team foundTeam=teamRepository.findByIdTeam(idTeam);
        Person foundPerson=personRepository.findByIdPerson(idPerson);
        foundTeam.getMembers().add(foundPerson);
        this.teamRepository.save(foundTeam);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value="/person/{idPerson}", method=RequestMethod.POST)
    public ResponseEntity getActiveUserTeam (@PathVariable long idPerson){
        
        Team foundTeam=teamRepository.findByActiveAndMembers_IdPerson(1, idPerson);
        Person foundPerson=personRepository.findByIdPerson(idPerson);
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO, "get user team on /team/person" + idPerson);
        return (foundTeam!=null)? new ResponseEntity(foundTeam,HttpStatus.OK):
                                  new ResponseEntity(HttpStatus.NOT_FOUND);
            
    }
    
    @RequestMapping(value="/history/person/{idPerson}", method=RequestMethod.POST)
    public ResponseEntity getUserTeam (@PathVariable long idPerson){
        
        List<Team> foundTeam = new ArrayList<>();
        
        if(this.teamRepository.findByMembers_IdPerson(idPerson) != null)
            foundTeam= this.teamRepository.findByMembers_IdPerson(idPerson);
       
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO, "get user team on /team/person" + idPerson);
        
        return (foundTeam!=null)? new ResponseEntity(foundTeam,HttpStatus.OK)
                                 :new ResponseEntity(HttpStatus.NOT_FOUND);
 
    }
    
    @RequestMapping(value="/{idPerson}/code/{code}", method=RequestMethod.POST)
    public ResponseEntity<Long> addByCode(@PathVariable long idPerson, @PathVariable String code){
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO, "get code" + code);
        
        Team found = this.teamRepository.findByPassword(code);

        return (found != null)?new ResponseEntity(found.getIdTeam(), HttpStatus.OK)
                                :new ResponseEntity(HttpStatus.BAD_REQUEST);
 
=======
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
>>>>>>> eed3de0e1773a7ac958aadcfb479337957649e03
}
    
}
