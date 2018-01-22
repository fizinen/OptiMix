package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 20.1.2018..
 */

/**
 * Class for recipes with info of every raw.
 */
public class RecipeRaws implements Serializable{

    long idRecipeRaws;
    long recipeId;
    Recipe rawRecipeId;
    Raw recipeRawId;
    double rawAmount;

    public RecipeRaws() {

    }

    public RecipeRaws(long recipeId, Recipe rawRecipeId, Raw recipeRawId, double rawAmount) {
        this.recipeId = recipeId;
        this.rawRecipeId = rawRecipeId;
        this.recipeRawId = recipeRawId;
        this.rawAmount = rawAmount;
    }

    public RecipeRaws(Recipe rawRecipeId, Raw recipeRawId, double rawAmount){
        this.rawRecipeId = rawRecipeId;
        this.recipeRawId = recipeRawId;
        this.rawAmount = rawAmount;
    }

    public RecipeRaws(Recipe rawRecipeId){
        this.rawRecipeId = rawRecipeId;
    }

    public long getIdRecipeRaws() {
        return idRecipeRaws;
    }

    public void setIdRecipeRaws(long idRecipeRaws) {
        this.idRecipeRaws = idRecipeRaws;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public Recipe getRawRecipeId() {
        return rawRecipeId;
    }

    public void setRawRecipeId(Recipe rawRecipeId) {
        this.rawRecipeId = rawRecipeId;
    }

    public Raw getRecipeRawId() {
        return recipeRawId;
    }

    public void setRecipeRawId(Raw recipeRawId) {
        this.recipeRawId = recipeRawId;
    }

    public double getRawAmount() {
        return rawAmount;
    }

    public void setRawAmount(double rawAmount) {
        this.rawAmount = rawAmount;
    }



    @Override
    public String toString() {
        return this.recipeRawId.getRawName() + " " + this.rawAmount;
    }
}
