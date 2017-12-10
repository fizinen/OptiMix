package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;

import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.AnalysisFragment;
import hr.foi.air.optimix.optimix.SettingsActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Lenovo on 10.12.2017..
 */

public class CreateRecipeHandler  extends ResponseHandler {

    public CreateRecipeHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {

        if(response.getHttpCode() == 200) {

            getActivity().startActivity(new Intent(getActivity(), SettingsActivity.class));
            getActivity().finish();

            return true;
        } else {
            // show fail
            Toast.makeText(getActivity(),
                    "Material creation failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
