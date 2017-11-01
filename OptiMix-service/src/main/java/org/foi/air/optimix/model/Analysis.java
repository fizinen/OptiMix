/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_raw")
    private Raw rawId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "analysisId", fetch = FetchType.LAZY)
    private List<AnalysisLog> analysisLog;

    public Raw getRaw() {
        return rawId;
    }

    public void setRaw(Raw raw) {
        this.rawId = raw;
    }


    

    public List<AnalysisLog> getUserAnalysis() {
        return analysisLog;
    }

    public void setUserAnalysis(List<AnalysisLog> userAnalysis) {
        this.analysisLog = userAnalysis;
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

}
