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
    
    @Column(name = "material_code")
    private String materialCode;
    
    @Column(name = "material_name")
    private String materialName;
    
    @Column(name = "material_series")
    private String materialSeries;
    
    @Column(name = "expiration_date")
    private Date expirationDate;
    
    @Column(name = "measure")
    private String measure;
    
    @Column(name = "material_mass")
    private long materialMass;
    
    @JsonIgnore
    @OneToMany(mappedBy = "raw", fetch = FetchType.LAZY)
    private List<Recipe> recipe;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rawId", fetch = FetchType.LAZY)
    private List<Analysis> analysis;
    

    public List<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<Recipe> recipe) {
        this.recipe = recipe;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSeries() {
        return materialSeries;
    }

    public void setMaterialSeries(String materialSeries) {
        this.materialSeries = materialSeries;
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

    public long getMaterialMass() {
        return materialMass;
    }

    public void setMaterialMass(long materialMass) {
        this.materialMass = materialMass;
    }

    
    
    
    
    
}
