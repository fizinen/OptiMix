package hr.foi.air.optimix.webservice;

import java.io.Serializable;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

/**
 * Class ServiceParams
 * Class for operating with all parameters of Service call, response and every interaction with webservice.
 * Deals with strings for url, method, type and urlEncoding and with Serializable object.
 */
public class ServiceParams {

    private String url;
    private String method;
    private String type;
    private Serializable object;
    private String urlEncoded;

    public ServiceParams(String url, String method, Serializable object) {
        this.url = url;
        this.method = method;
        this.object = object;
        this.type = ServiceCaller.APPLICATION_JSON;
    }

    public ServiceParams(String url, String method, String type, Serializable object, String urlEncoded) {
        this.url = url;
        this.method = method;
        this.type = type;
        this.object = object;
        this.urlEncoded = urlEncoded;
    }

    public String getUrlEncoded() {
        return urlEncoded;
    }

    public void setUrlEncoded(String urlEncoded) {
        this.urlEncoded = urlEncoded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Serializable getObject() {
        return object;
    }

    public void setObject(Serializable object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return this.url + " " + this.method;
    }


}
