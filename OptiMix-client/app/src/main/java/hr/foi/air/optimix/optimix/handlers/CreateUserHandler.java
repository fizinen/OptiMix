package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.app.Fragment;
import android.widget.Toast;

import java.io.Serializable;

import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.CreateUserActivity;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class CreateUserHandler extends ResponseHandler {

    public CreateUserHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    public CreateUserHandler(Fragment fragment, Serializable... args) {
        super(fragment, args);
    }


    @Override
    public boolean handleResponse(ServiceResponse response) {
        Person credentials = (Person) getArgs()[0];

        if(response.getHttpCode() == 200) {

            LoginHandler loginHandler = new LoginHandler(getActivity());
            ServiceParams params = new ServiceParams(
                    getActivity().getString(hr.foi.air.optimix.webservice.R.string.person_login_path),
                    ServiceCaller.HTTP_POST, credentials);
            new ServiceAsyncTask(loginHandler).execute(params);
            return true;
        } else {

            // show fail
            Toast.makeText(getActivity(),
                    "Creating user failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}