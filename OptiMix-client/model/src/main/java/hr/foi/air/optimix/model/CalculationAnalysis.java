package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 20.1.2018..
 */

public class CalculationAnalysis implements Serializable {

    private long idCalculationAnalysis;
    private long calculationId;
    private Calculation calculationAnalysisId;
    private Analysis analysisCalculationId;
    private double amountForCalculation;

    public CalculationAnalysis(){

    }

    public CalculationAnalysis(Calculation calculation, Analysis analysis, double amountForCalculation){
        this.calculationAnalysisId = calculation;
        this.analysisCalculationId = analysis;
        this.amountForCalculation = amountForCalculation;

    }

    public long getIdCalculationAnalysis() {
        return idCalculationAnalysis;
    }

    public void setIdCalculationAnalysis(long idCalculationAnalysis) {
        this.idCalculationAnalysis = idCalculationAnalysis;
    }

    public long getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(long calculationId) {
        this.calculationId = calculationId;
    }

    public Calculation getCalculationAnalysisId() {
        return calculationAnalysisId;
    }

    public void setCalculationAnalysisId(Calculation calculationAnalysisId) {
        this.calculationAnalysisId = calculationAnalysisId;
    }

    public Analysis getAnalysisCalculationId() {
        return analysisCalculationId;
    }

    public void setAnalysisCalculationId(Analysis analysisCalculationId) {
        this.analysisCalculationId = analysisCalculationId;
    }

    public double getAmountForCalculation() {
        return amountForCalculation;
    }

    public void setAmountForCalculation(double amountForCalculation) {
        this.amountForCalculation = amountForCalculation;
    }

}
