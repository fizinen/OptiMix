package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gloria Babić on 8.12.2017..
 */

public class Analysis implements Serializable {

    long idAnalysis;
    private double water;
    private double fat;
    private double proteins;
    private Material raw;

    public Material getRaw() {
        return raw;
    }

    public void setRaw(Material raw) {
        this.raw = raw;
    }

    //private List<AnalysisLog> analysisLog;


    public Analysis(double water, double fat, double proteins, Material raw) {
        this.water = water;
        this.fat = fat;
        this.proteins = proteins;
        this.raw = raw;
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

}
