package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.net.ResponseCache;

import hr.foi.air.optimix.core.SessionManager;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.MainActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Gloria Babić on 31.10.2017..
 */

public class LoginHandler  extends ResponseHandler {

    public LoginHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {


        if(response.getHttpCode() == 200) {

            Person person = new Gson().fromJson(response.getJsonResponse(), Person.class);

            SessionManager manager = SessionManager.getInstance(getActivity());
            if(manager.createSession(person, SessionManager.PERSON_INFO_KEY)) {

                Person sessionPerson = manager.retrieveSession(SessionManager.PERSON_INFO_KEY, Person.class);


                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                return true;

            } else {
                Toast.makeText(getActivity(),
                        "Internal application error, please try again", Toast.LENGTH_LONG).show();
                return false;
            }

        } else  {
            Toast.makeText(getActivity(), "Invalid credentials", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}