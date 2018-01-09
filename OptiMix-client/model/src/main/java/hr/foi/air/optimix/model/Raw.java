package hr.foi.air.optimix.model;

import java.io.Serializable;

/**
 * Created by erdel on 5.12.2017..
 */

public class Raw implements Serializable{
    long idRaw;
    String rawCode;
    String rawName;
    String rawSeries;
    String expirationDate;
    String measure;
    long rawMass;

    public Raw() {
    }

    public Raw(long idRaw, String rawName) {
        this.idRaw = idRaw;
        this.rawName = rawName;
    }

    public Raw(long idRaw, String rawName, long rawMass) {
        this.idRaw = idRaw;
        this.rawName = rawName;
        this.rawMass = rawMass;
    }

    public Raw(String rawCode, String rawName, String rawSeries, String expirationDate, long rawMass) {
        this.rawCode = rawCode;
        this.rawName = rawName;
        this.rawSeries = rawSeries;
        this.expirationDate = expirationDate;
        this.rawMass = rawMass;
    }

    public long getIdRaw() {
        return idRaw;
    }

    public void setIdRaw(long idRaw) {
        this.idRaw = idRaw;
    }

    public String getRawCode() {
        return rawCode;
    }

    public void setRawCode(String rawCode) {
        this.rawCode = rawCode;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getRawSeries() {
        return rawSeries;
    }

    public void setRawSeries(String rawSeries) {
        this.rawSeries = rawSeries;
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

    public double getRawMass() {
        return rawMass;
    }

    public void setRawMass(long rawMass) {
        this.rawMass = rawMass;
    }

    @Override
    public String toString() {
        return this.getRawName();
    }
}
