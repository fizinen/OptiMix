package hr.foi.air.optimix.webservice;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class ServiceResponse {

    int httpCode;
    String cookie;
    String jsonResponse;

    public ServiceResponse() {
    }

    public ServiceResponse(int httpCode, String jsonResponse) {
        this.httpCode = httpCode;
        this.jsonResponse = jsonResponse;
    }

    public ServiceResponse(String cookie, String jsonResponse, int httpCode) {
        this.cookie = cookie;
        this.jsonResponse = jsonResponse;
        this.httpCode = httpCode;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    @Override
    public String toString() {
        return this.httpCode + " " + this.jsonResponse;
    }
}
