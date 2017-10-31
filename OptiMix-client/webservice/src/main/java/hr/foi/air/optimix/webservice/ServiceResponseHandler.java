package hr.foi.air.optimix.webservice;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public interface  ServiceResponseHandler extends SimpleResponseHandler{

    void onPreSend();
    void onPostSend();

}
