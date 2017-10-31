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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Gloria Babić
 */
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe")
    long idRecipe;

    @Column(name = "recipe_mass")
    private double recipeMass;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe_log", fetch = FetchType.LAZY)
    private List<RecipeLog> recipeLog;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "raw")
    private Raw raw;

    public List<RecipeLog> getRecipe() {
        return recipeLog;
    }

    public void setRecipe(List<RecipeLog> recipe) {
        this.recipeLog = recipe;
    }

    public Raw getRaw() {
        return raw;
    }

    public void setRaw(Raw raw) {
        this.raw = raw;
    }
    
    

    public long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public double getRecipeMass() {
        return recipeMass;
    }

    public void setRecipeMass(double recipeMass) {
        this.recipeMass = recipeMass;
    }

}
