package hr.foi.air.optimix.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lenovo on 20.1.2018..
 */
/**
 * Class for raws.
 */
public class Raw implements Serializable{
    long idRaw;
    String rawCode;
    String rawName;

    public Raw() {
    }

    public Raw(String rawCode, String rawName) {
        this.rawCode = rawCode;
        this.rawName = rawName;
    }

    public Raw(String rawCode) {
        this.rawCode = rawCode;
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


    @Override
    public String toString() {
        return this.getRawName();
    }
}
