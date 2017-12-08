/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import javax.persistence.Table;
import org.foi.air.optimix.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gloria Babić
 */
@Repository
@Table(name = "analysis")
public interface AnalysisRepository extends JpaRepository<Analysis, String> {

    public Analysis findByIdAnalysis(long id);

    public Analysis findByIdRaw(long id);

}
