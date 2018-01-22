package hr.foi.air.optimix.webservice;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

/**
 * Interface ServiceResponseHandler that extends SimpleResponseHandler
 * Add functions that happen before and after send.
 */
public interface  ServiceResponseHandler extends SimpleResponseHandler{

    void onPreSend();
    void onPostSend();

}
