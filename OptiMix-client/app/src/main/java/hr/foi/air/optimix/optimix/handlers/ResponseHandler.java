package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.app.Fragment;

import java.io.Serializable;

import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.CreateUserActivity;
import hr.foi.air.optimix.webservice.ServiceResponseHandler;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public abstract class ResponseHandler  implements ServiceResponseHandler {

    private Fragment fragment;
    private Serializable[] args;
    private Activity activity;


    public ResponseHandler(Activity activity, Serializable... args) {
        this.activity = activity;
        this.args = args;

    }
    public ResponseHandler(Fragment fragment, Serializable... args) {
        this.fragment = fragment;
        this.args = args;

    }

    @Override
    public void onPreSend() {

    }

    @Override
    public void onPostSend() {

    }

    public Fragment getFragment() {
        return fragment;
    }

    public Activity getActivity() {
        return activity;
    }

    public Object[] getArgs() {
        return args;
    }


}
