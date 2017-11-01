/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;
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
 * @author Gloria Babić
 */
@Entity
@Table(name = "recipe_log")
public class RecipeLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipe_log")
    long idRecipeLog;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_recipe")
    private Recipe recipeId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_person")
    private Person userId;

    @Column(name = "input_date")
    private Date inputDate;

    public long getIdRecipeLog() {
        return idRecipeLog;
    }

    public void setIdRecipeLog(long idRecipeLog) {
        this.idRecipeLog = idRecipeLog;
    }

    public Recipe getRecipe() {
        return recipeId;
    }

    public void setRecipe(Recipe recipe) {
        this.recipeId = recipe;
    }

    public Person getUser() {
        return userId;
    }

    public void setUser(Person user) {
        this.userId = user;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

}
