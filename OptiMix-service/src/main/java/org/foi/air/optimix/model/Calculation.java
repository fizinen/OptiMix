/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "calculation")
public class Calculation implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calculation")
    long idCalculation;

    public long getIdCalculation() {
        return idCalculation;
    }

    public void setIdCalculation(long idCalculation) {
        this.idCalculation = idCalculation;
    }
    
   @Column(name = "kolicina_proizvoda")
   private double kolicinaProizvoda;

    public double getKolicinaProizvoda() {
        return kolicinaProizvoda;
    }

    public void setKolicinaProizvoda(double kolicinaProizvoda) {
        this.kolicinaProizvoda = kolicinaProizvoda;
    }
   
    @JsonIgnore
    @OneToMany(mappedBy = "calculationAnalysisId", fetch = FetchType.LAZY)
    private List<CalculationAnalysis> calculationAnalysis;

    public List<CalculationAnalysis> getCalculationAnalysis() {
        return calculationAnalysis;
    }

    public void setCalculationAnalysis(List<CalculationAnalysis> calculationAnalysis) {
        this.calculationAnalysis = calculationAnalysis;
    }
    
    
    
   
    
}
