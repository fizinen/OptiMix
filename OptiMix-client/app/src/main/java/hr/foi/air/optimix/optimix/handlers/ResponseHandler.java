package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;

import java.io.Serializable;

import hr.foi.air.optimix.webservice.ServiceResponseHandler;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public abstract class ResponseHandler  implements ServiceResponseHandler {

    private Activity activity;
    private Object[] args;


    public ResponseHandler(Activity activity, Serializable... args) {
        this.activity = activity;
        this.args = args;

    }

    @Override
    public void onPreSend() {

    }

    @Override
    public void onPostSend() {

    }

    public Activity getActivity() {
        return activity;
    }

    public Object[] getArgs() {
        return args;
    }


}
