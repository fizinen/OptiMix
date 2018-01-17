/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import javax.persistence.Table;
import org.foi.air.optimix.model.Raw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */

@Repository
@Table(name = "raw")

public interface RawRepository extends JpaRepository<Raw, String> {

    public Raw findByIdRaw(long id);

    public Raw findByRawName(String rawName);
    
    public Raw findByRawCode(String rawCode);

}
