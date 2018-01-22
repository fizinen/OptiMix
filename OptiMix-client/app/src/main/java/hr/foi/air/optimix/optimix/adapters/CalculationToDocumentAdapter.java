package hr.foi.air.optimix.optimix.adapters;

import java.util.List;

import hr.foi.air.optimix.core.DocumentCreationData;
import hr.foi.air.optimix.model.CalculationAnalysis;
import hr.foi.air.optimix.model.Recipe;

/**
 * Created by erdel on 22.1.2018..
 */

public class CalculationToDocumentAdapter implements DocumentCreationData {
    List<CalculationAnalysis> listOfAnalysis;
    Recipe temporaryRecipe;
    public CalculationToDocumentAdapter(List<CalculationAnalysis> listOfAnalysis) {
        this.listOfAnalysis = listOfAnalysis;
    }
    @Override
    public String getName(){
        String name;
        CalculationAnalysis calculationAnalysis = listOfAnalysis.get(0);
        name = calculationAnalysis.getCalculationAnalysisId().getRecipeId().getRecipeName();
        return name;
    }
    @Override
    public String getContent(){
        String content="";
        double amount;
        String name;
        for(CalculationAnalysis ca : listOfAnalysis){
            amount = ca.getAmountForCalculation();
            name = ca.getAnalysisCalculationId().getRawId().getRawName(); //ime
            content += "Sastojak: - \t" + name + " - Količina: "+amount +"kg\n";
        }
        return content;
    }
}
