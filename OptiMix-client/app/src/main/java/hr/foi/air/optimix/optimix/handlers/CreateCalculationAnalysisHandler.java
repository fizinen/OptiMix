package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;

import hr.foi.air.optimix.model.CalculationAnalysis;
import hr.foi.air.optimix.optimix.MainActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Lenovo on 21.1.2018..
 */

public class CreateCalculationAnalysisHandler extends ResponseHandler{

    public CreateCalculationAnalysisHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {

        if (response.getHttpCode() == 200) {

            getFragment();

            return true;
        } else {
            // show fail
            Toast.makeText(getActivity(),
                    "CalculationAnalysis creation failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
