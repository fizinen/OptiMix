/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.repositories;

import java.util.List;
import javax.persistence.Table;
import org.foi.air.optimix.model.CalculationAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
@Table(name = "calculation_analysis")
public interface CalculationAnalysisRepository extends JpaRepository<CalculationAnalysis, String>{
    
    public CalculationAnalysis findByIdCalculationAnalysis(long id);
    
    public List<CalculationAnalysis> findAllByCalculationId(long calculationId);
    
}
