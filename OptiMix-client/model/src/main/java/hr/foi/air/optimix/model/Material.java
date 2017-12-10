package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by erdel on 5.12.2017..
 */

public class Material implements Serializable{
    long idRaw;
    String materialCode;
    String materialName;
    String materialSeries;
    String expirationDate;
    String measure;
    long materialMass;

    public Material() {
    }

    public Material(long idRaw, String materialName) {
        this.idRaw = idRaw;
        this.materialName = materialName;
    }

    public Material(long idRaw, String materialName, long materialMass) {
        this.idRaw = idRaw;
        this.materialName = materialName;
        this.materialMass = materialMass;
    }

    public Material(String materialCode, String materialName, String materialSeries, String expirationDate, long materialMass) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialSeries = materialSeries;
        this.expirationDate = expirationDate;
        this.materialMass = materialMass;
    }

    public long getIdRaw() {
        return idRaw;
    }

    public void setIdRaw(long idRaw) {
        this.idRaw = idRaw;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getMaterialMass() {
        return materialMass;
    }

    public void setMaterialMass(long materialMass) {
        this.materialMass = materialMass;
    }

    @Override
    public String toString() {
        return this.getMaterialName();
    }
}
