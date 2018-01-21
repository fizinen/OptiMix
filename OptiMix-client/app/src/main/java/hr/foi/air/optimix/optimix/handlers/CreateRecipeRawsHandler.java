package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;

import hr.foi.air.optimix.optimix.CreateRecipeActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Gloria Babić on 21.1.2018..
 */

public class CreateRecipeRawsHandler extends ResponseHandler {

    public CreateRecipeRawsHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {

        if(response.getHttpCode() == 200) {


            return true;
        } else {
            // show fail
            Toast.makeText(getActivity(),
                    "RecipeRaws creation failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}