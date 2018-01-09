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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 */
@Entity
@Table(name="raw")
public class Raw implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raw")
    long idRaw;
    
    @Column(name = "raw_code")
    private String rawCode;
    
    @Column(name = "raw_name")
    private String rawName;
    
    @Column(name = "raw_series")
    private String rawSeries;
    
    @Column(name = "expiration_date")
    private Date expirationDate;
    
    @Column(name = "measure")
    private String measure;
    
    @Column(name = "raw_mass")
    private long rawMass;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "raws")
    @JsonIgnore
    private List<Recipe> partOfRecipe;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rawId", fetch = FetchType.LAZY)
    private List<Analysis> analysis;

    public List<Recipe> getPartOfRecipe() {
        return partOfRecipe;
    }

    public void setPartOfRecipe(List<Recipe> partOfRecipe) {
        this.partOfRecipe = partOfRecipe;
    }
    

  

    public List<Analysis> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(List<Analysis> analysis) {
        this.analysis = analysis;
    }
    
    

    public long getIdRaw() {
        return idRaw;
    }

    public void setIdRaw(long idRaw) {
        this.idRaw = idRaw;
    }

    public String getRawCode() {
        return rawCode;
    }

    public void setRawCode(String rawCode) {
        this.rawCode = rawCode;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getRawSeries() {
        return rawSeries;
    }

    public void setRawSeries(String rawSeries) {
        this.rawSeries = rawSeries;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public long getRawMass() {
        return rawMass;
    }

    public void setRawMass(long rawMass) {
        this.rawMass = rawMass;
    }

    
    
    
    
    
}
