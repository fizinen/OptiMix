/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import javax.persistence.Table;
import org.foi.air.optimix.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gloria Babić
 */
@Repository
@Table(name = "credentials")
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    
    // SELECT * FROM credentials WHERE username = :username
    Credentials findByUsername(String username);
}
