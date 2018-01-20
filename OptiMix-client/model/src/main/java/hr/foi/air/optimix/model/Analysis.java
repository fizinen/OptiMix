package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gloria Babić on 8.12.2017..
 */

public class Analysis implements Serializable {

    private long idAnalysis;
    private double water;
    private double fat;
    private double proteins;
    private String expirationDate;
    private long analysisRawMass;
    private Raw rawId;

    public Raw getRawId() {
        return rawId;
    }

    public void setRawId(Raw rawId) {
        this.rawId = rawId;
    }

    //private List<AnalysisLog> analysisLog;


    public Analysis(double water, double fat, double proteins, String expirationDate, long analysisRawMass,  Raw raw) {
        this.water = water;
        this.fat = fat;
        this.proteins = proteins;
        this.expirationDate = expirationDate;
        this.analysisRawMass = analysisRawMass;
        this.rawId = raw;

    }

    public long getIdAnalysis() {
        return idAnalysis;
    }

    public void setIdAnalysis(long idAnalysis) {
        this.idAnalysis = idAnalysis;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getAnalysisRawMass() {
        return analysisRawMass;
    }

    public void setAnalysisRawMass(long analysisRawMass) {
        this.analysisRawMass = analysisRawMass;
    }

}
