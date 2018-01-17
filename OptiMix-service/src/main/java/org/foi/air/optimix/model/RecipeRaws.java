/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
@Table(name = "recipe_raw")
public class RecipeRaws implements Serializable{
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe_raws")
    long idRecipeRaws;

    public long getIdRecipeRaws() {
        return idRecipeRaws;
    }
   
    public void setIdRecipeRaws(long idRecipeRaws) {
        this.idRecipeRaws = idRecipeRaws;
    }
    */

    
    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    
    @Id
    @ManyToOne
    @JoinColumn(name = "raw_id")
    private Raw raw;

    public Raw getRaw() {
        return raw;
    }

    public void setRaw(Raw raw) {
        this.raw = raw;
    }

    
    @Column(name = "raw_amount")
    private double rawAmount;

    public void setRawAmount(double rawAmount) {
        this.rawAmount = rawAmount;
    }

    public double getRawAmount() {
        return rawAmount;
    }
    
    
    
}
