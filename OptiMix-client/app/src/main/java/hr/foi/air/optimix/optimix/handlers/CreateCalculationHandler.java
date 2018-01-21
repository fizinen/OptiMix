package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;


import hr.foi.air.optimix.model.Calculation;

import hr.foi.air.optimix.optimix.CalculationFragment;
import hr.foi.air.optimix.optimix.MainActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Lenovo on 21.1.2018..
 */

public class CreateCalculationHandler extends ResponseHandler {

    public CreateCalculationHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {
        Calculation calculation = (Calculation) getArgs()[0];

        if (response.getHttpCode() == 200) {

            getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();

            return true;
        } else {
            // show fail
            Toast.makeText(getActivity(),
                    "Calculation creation failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
