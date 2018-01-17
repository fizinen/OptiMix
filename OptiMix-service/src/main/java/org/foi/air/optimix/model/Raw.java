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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 */
@Entity
@Table(name = "raw")
public class Raw implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raw")
    long idRaw;

    @Column(name = "raw_code")
    private String rawCode;

    @Column(name = "raw_name")
    private String rawName;

    /*
    @JsonIgnore
    @OneToMany(mappedBy = "raws", fetch = FetchType.LAZY)
    private List<RecipeRaws> recipeRaws;

    public List<RecipeRaws> getRecipeRaws() {
        return recipeRaws;
    }

    public void setRecipeRaws(List<RecipeRaws> recipeRaws) {
        this.recipeRaws = recipeRaws;
    }
     */
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "raws")
    @JsonIgnore
    private List<Recipe> partOfRecipe;

    public List<Recipe> getPartOfRecipe() {
        return partOfRecipe;
    }

    public void setPartOfRecipe(List<Recipe> partOfRecipe) {
        this.partOfRecipe = partOfRecipe;
    }
    

    @JsonIgnore
    @OneToMany(mappedBy = "rawId", fetch = FetchType.LAZY)
    private List<Analysis> analysis;

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

}
