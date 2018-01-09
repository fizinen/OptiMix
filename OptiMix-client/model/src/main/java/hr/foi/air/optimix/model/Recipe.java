package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by erdel on 5.12.2017..
 */

public class Recipe implements Serializable {
    long idRecipe;
    String recipeName;
    List<Raw> listOfRecipeRaws;
    Double evaporationCoefficient;

    public Recipe() {
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

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

    public Double getEvaporationCoefficient() {
        return evaporationCoefficient;
    }

    public void setEvaporationCoefficient(Double evaporationCoefficient) {
        this.evaporationCoefficient = evaporationCoefficient;
    }

    public void addMaterial(Raw addedRaw){
        listOfRecipeRaws.add(addedRaw);
    }

    public List<Raw> getListOfRecipeRaws() {
        return listOfRecipeRaws;
    }

    public void setListOfRecipeRaws(List<Raw> listOfRecipeRaws) {
        this.listOfRecipeRaws = listOfRecipeRaws;
    }

//listOfRecipeRaws.object.rawMass, object.idRaw and idRecipe are all added into sirovina_recept table
    //recept table contains only idRecipe, recipeName and evaporationCoefficient
}
