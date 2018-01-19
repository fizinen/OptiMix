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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Gloria Babić
 * @author Lenovo
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    long idRecipe;

    @Column(name = "recipe_name")
    private String recipeName;

    @JsonIgnore
    @OneToMany(mappedBy = "recipeIdLog", fetch = FetchType.LAZY)
    private List<RecipeLog> recipeLog;
    
    @JsonIgnore
    @OneToMany(mappedBy = "recipeId", fetch = FetchType.LAZY)
    private List<Calculation> calculations;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "rawRecipeId", fetch = FetchType.LAZY)
    private List<RecipeRaws> recipeRaws;
    
    public List<RecipeRaws> getRecipeRaws() {
        return recipeRaws;
    }

    public void setRecipeRaws(List<RecipeRaws> recipeRaws) {
        this.recipeRaws = recipeRaws;
    }
    
    /*

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "raw_recipe", joinColumns = {
        @JoinColumn(name = "id_recipe", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "id_raw", nullable = false, updatable = false)})
    private List<Raw> raws;

    public List<Raw> getRaws() {
        return raws;
    }

    public void setRaws(List<Raw> raws) {
        this.raws = raws;
    }
    
    */
    public long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeLog> getRecipeLog() {
        return recipeLog;
    }

    public void setRecipeLog(List<RecipeLog> recipeLog) {
        this.recipeLog = recipeLog;
    }

    public List<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(List<Calculation> calculations) {
        this.calculations = calculations;
    }
    
    

}
