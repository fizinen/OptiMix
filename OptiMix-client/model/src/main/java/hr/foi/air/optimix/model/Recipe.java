package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by erdel on 5.12.2017..
 */

public class Recipe implements Serializable {
    long idRecipe;
    String recipeName;
    List<Material> listOfRecipeMaterials;
    Double evaporationCoefficient;

    public Recipe() {
    }

    public Recipe(String recipeName, Double evaporationCoefficient) {
        this.recipeName = recipeName;
        this.evaporationCoefficient = evaporationCoefficient;
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

    public void addMaterial(Material addedMaterial){
        listOfRecipeMaterials.add(addedMaterial);
    }

    public List<Material> getListOfRecipeMaterials() {
        return listOfRecipeMaterials;
    }

    public void setListOfRecipeMaterials(List<Material> listOfRecipeMaterials) {
        this.listOfRecipeMaterials = listOfRecipeMaterials;
    }

//listOfRecipeMaterials.object.materialMass, object.idRaw and idRecipe are all added into sirovina_recept table
    //recept table contains only idRecipe, recipeName and evaporationCoefficient
}
