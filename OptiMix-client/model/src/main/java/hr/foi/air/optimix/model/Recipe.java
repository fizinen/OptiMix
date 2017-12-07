package hr.foi.air.optimix.model;

import java.util.List;

/**
 * Created by erdel on 5.12.2017..
 */

public class Recipe {
    long recipeId;
    String recipeName;
    List<Material> listOfRecipeMaterials;
    Double evaporationCoefficient;

    public Recipe() {
    }

    public Recipe(String recipeName, Double evaporationCoefficient) {
        this.recipeName = recipeName;
        this.evaporationCoefficient = evaporationCoefficient;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
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

    public void addMaterial(Material addedMaterial){
        listOfRecipeMaterials.add(addedMaterial);
    }


    //listOfRecipeMaterials.object.materialAmount, object.materialId and recipeId are all added into sirovina_recept table
    //recept table contains only recipeId, recipeName and evaporationCoefficient
}
