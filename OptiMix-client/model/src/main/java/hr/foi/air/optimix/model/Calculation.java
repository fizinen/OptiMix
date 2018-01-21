package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 20.1.2018..
 */

public class Calculation implements Serializable{

    private long idCalculation;
    private double calculationFullAmount;
    private Recipe recipeId;

    public Calculation(){

    }

    public Calculation(double calculationFullAmount, Recipe recipe){
        this.calculationFullAmount = calculationFullAmount;
        this.recipeId = recipe;
    }


    public long getIdCalculation() {
        return idCalculation;
    }

    public void setIdCalculation(long idCalculation) {
        this.idCalculation = idCalculation;
    }

    public double getCalculationFullAmount() {
        return calculationFullAmount;
    }

    public void setCalculationFullAmount(double calculationFullAmount) {
        this.calculationFullAmount = calculationFullAmount;
    }

    public Recipe getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Recipe recipeId) {
        this.recipeId = recipeId;
    }

}
