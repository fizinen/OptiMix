/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import javax.persistence.Table;
import org.foi.air.optimix.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gloria Babić
 */
@Repository
@Table(name = "person")
public interface PersonRepository extends JpaRepository<Person, String> {

    public Person findByIdPerson(long id);

    public Person findByCredentialsUsername(String username);

}
