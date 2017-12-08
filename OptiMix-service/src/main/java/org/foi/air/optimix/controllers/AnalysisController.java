/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gloria Babić
 */
@RestController
@RequestMapping(value = "/analysis")
public class AnalysisController {
    
    
    
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
 
}
    
}
