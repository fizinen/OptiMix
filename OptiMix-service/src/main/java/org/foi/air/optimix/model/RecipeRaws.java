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
    
    
    @Column(name = "recipe_id")
    long recipeId;

    public long getRecipeId() {
        return recipeId;
    }
    
    @ManyToOne
    @JoinColumn(name = "recipe")
    private Recipe rawRecipeId;

    public Recipe getRawRecipeId() {
        return rawRecipeId;
    }
    
    public void setRawRecipeId(Recipe rawRecipeId) {
        this.rawRecipeId = rawRecipeId;
        this.recipeId = rawRecipeId.getIdRecipe();
    }
    
    @ManyToOne
    @JoinColumn(name = "raw")
    private Raw recipeRawId;

    public Raw getRecipeRawId() {
        return recipeRawId;
    }

    public void setRecipeRawId(Raw recipeRawId) {
        this.recipeRawId = recipeRawId;
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
