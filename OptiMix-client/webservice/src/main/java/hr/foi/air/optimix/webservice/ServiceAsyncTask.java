package hr.foi.air.optimix.webservice;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class ServiceAsyncTask extends AsyncTask<ServiceParams, Void, ServiceResponse> {

    ServiceParams sp;

    static final String mainUrl = "http://45.77.64.181:8080";
    SimpleResponseHandler handler;

    public ServiceAsyncTask(SimpleResponseHandler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(handler instanceof ServiceResponseHandler)
            ((ServiceResponseHandler)handler).onPreSend();
    }

    @Override
    protected ServiceResponse doInBackground(ServiceParams... params) {
        sp = params[0];
        ServiceResponse jsonResponse = null;


        try {
            URL url = new URL(mainUrl+sp.getUrl());
            String method = sp.getMethod();
            Serializable object = sp.getObject();
            jsonResponse = ServiceCaller.call(url, method, object, sp.getType(), sp.getUrlEncoded());
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(ServiceResponse s) {
        if(sp != null && handler != null) {

            handler.handleResponse(s);
            if(handler instanceof ServiceResponseHandler)
                ((ServiceResponseHandler)handler).onPostSend();
        } else {

        }
    }
}