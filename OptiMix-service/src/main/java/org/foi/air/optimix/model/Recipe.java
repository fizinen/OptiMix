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

    @Column(name = "recipe_mass")
    private double recipeMass;

    @JsonIgnore
    @OneToMany(mappedBy = "recipeId", fetch = FetchType.LAZY)
    private List<RecipeLog> recipeLog;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "raw_recipe", joinColumns = {
        @JoinColumn(name = "id_recipe", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "id_raw", nullable = false, updatable = false)})
    private List<Raw> raws;

    public List<RecipeLog> getRecipe() {
        return recipeLog;
    }

    public void setRecipe(List<RecipeLog> recipe) {
        this.recipeLog = recipe;
    }

    public List<RecipeLog> getRecipeLog() {
        return recipeLog;
    }

    public void setRecipeLog(List<RecipeLog> recipeLog) {
        this.recipeLog = recipeLog;
    }

    public List<Raw> getRaws() {
        return raws;
    }

    public void setRaws(List<Raw> raws) {
        this.raws = raws;
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

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

}
