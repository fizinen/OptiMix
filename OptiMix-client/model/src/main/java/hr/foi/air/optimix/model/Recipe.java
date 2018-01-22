package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by erdel on 5.12.2017..
 */

/**
 * Class for recipes.
 */
public class Recipe implements Serializable {
    long idRecipe;
    String recipeName;

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

}
