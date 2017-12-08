package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gloria Babić on 8.12.2017..
 */

public class Raw implements Serializable {

    long idRaw;
    String materialCode;
    String materialName;
    String materialSeries;
    Date expirationDate;
    String measure;
    long materialMass;

    public Raw() {
    }

    public Raw(String materialName) {
        this.materialName = materialName;
    }


    public Raw(long idRaw, String materialCode, String materialName, String materialSeries, Date expirationDate, String measure, long materialMass) {
        this.idRaw = idRaw;
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialSeries = materialSeries;
        this.expirationDate = expirationDate;
        this.measure = measure;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public long getMaterialMass() {
        return materialMass;
    }

    public void setMaterialMass(long materialMass) {
        this.materialMass = materialMass;
    }
}
