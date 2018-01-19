/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "calculation_analysis")
public class CalculationAnalysis implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calculation_analysis")
    long idCalculationAnalysis;

    public long getIdCalculationAnalysis() {
        return idCalculationAnalysis;
    }

    @Column(name = "calculation_id")
    long calculationId;

    public long getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(long calculationId) {
        this.calculationId = calculationId;
    }
    
    @ManyToOne
    @JoinColumn(name = "calculation")
    private Calculation calculationAnalysisId;

    public Calculation getCalculationAnalysisId() {
        return calculationAnalysisId;
    }

    public void setCalculationAnalysisId(Calculation calculationAnalysisId) {
        this.calculationAnalysisId = calculationAnalysisId;
        this.calculationId = calculationAnalysisId.getIdCalculation();
    }
    
    @ManyToOne
    @JoinColumn(name = "analysis")
    private Analysis analysisCalculationId;

    public Analysis getAnalysisCalculationId() {
        return analysisCalculationId;
    }

    public void setAnalysisCalculationId(Analysis analysisCalculationId) {
        this.analysisCalculationId = analysisCalculationId;
    }
    
    @Column(name = "amount_for_calculation")
    private double amountForCalculation;

    public double getAmountForCalculation() {
        return amountForCalculation;
    }

    public void setAmountForCalculation(double amountForCalculation) {
        this.amountForCalculation = amountForCalculation;
    }  
            
    
}
