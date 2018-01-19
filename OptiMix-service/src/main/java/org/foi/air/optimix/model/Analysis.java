/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 */
@Entity
@Table(name="analysis")
public class Analysis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analysis")
    long idAnalysis;

    @Column(name = "water")
    private double water;

    @Column(name = "fat")
    private double fat;

    @Column(name = "proteins")
    private double proteins;
    
    @Column(name = "expiration_date")
    private Date expirationDate;
    
    @Column(name = "raw_mass")
    private long analysisRawMass;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_raw")
    private Raw rawId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "analysisId", fetch = FetchType.LAZY)
    private List<AnalysisLog> analysisLog;
    
    @JsonIgnore
    @OneToMany(mappedBy = "analysisCalculationId", fetch = FetchType.LAZY)
    private List<CalculationAnalysis> calculationAnalysis;

    public List<CalculationAnalysis> getCalculationAnalysis() {
        return calculationAnalysis;
    }

    public void setCalculationAnalysis(List<CalculationAnalysis> calculationAnalysis) {
        this.calculationAnalysis = calculationAnalysis;
    }

    public Raw getRawId() {
        return rawId;
    }

    public void setRawId(Raw rawId) {
        this.rawId = rawId;
    }
    
    
    public List<AnalysisLog> getAnalysisLog() {
        return analysisLog;
    }

    public void setAnalysisLog(List<AnalysisLog> analysisLog) {
        this.analysisLog = analysisLog;
    }

    
    public long getIdAnalysis() {
        return idAnalysis;
    }

    public void setIdAnalysis(long idAnalysis) {
        this.idAnalysis = idAnalysis;
    }

    
    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    
    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    
    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
    
    
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    
    public long getAnalysisRawMass() {
        return analysisRawMass;
    }

    public void setAnalysisRawMass(long analysisRawMass) {
        this.analysisRawMass = analysisRawMass;
    }

}
