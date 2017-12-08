package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by erdel on 5.12.2017..
 */

public class Material implements Serializable{
    long materialId;
    String materialNumber;
    String materialName;
    String materialSeries;
    Date materialDateBefore;
    String measurement;
    double materialAmount;

    public Material() {
    }

    public Material(long materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }

    public Material(long materialId, String materialName, double materialAmount) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.materialAmount = materialAmount;
    }

    public long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSeries() {
        return materialSeries;
    }

    public void setMaterialSeries(String materialSeries) {
        this.materialSeries = materialSeries;
    }

    public Date getMaterialDateBefore() {
        return materialDateBefore;
    }

    public void setMaterialDateBefore(Date materialDateBefore) {
        this.materialDateBefore = materialDateBefore;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public double getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(double materialAmount) {
        this.materialAmount = materialAmount;
    }

    @Override
    public String toString() {
        return this.getMaterialName();
    }
}
