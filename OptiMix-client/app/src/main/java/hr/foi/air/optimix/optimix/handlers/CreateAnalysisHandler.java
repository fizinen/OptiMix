package hr.foi.air.optimix.optimix.handlers;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;

import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.optimix.AnalysisFragment;
import hr.foi.air.optimix.optimix.MainActivity;
import hr.foi.air.optimix.webservice.ServiceResponse;

/**
 * Created by Gloria Babić on 10.12.2017..
 */

public class CreateAnalysisHandler extends ResponseHandler {

    public CreateAnalysisHandler(Activity activity, Serializable... args) {
        super(activity, args);
    }

    @Override
    public boolean handleResponse(ServiceResponse response) {
        Analysis analysis = (Analysis) getArgs()[0];

        if (response.getHttpCode() == 200) {

            getActivity().startActivity( (new Intent(getActivity(), MainActivity.class)));
            getActivity().finish();

            return true;
        } else {
            // show fail
            Toast.makeText(getActivity(),
                    "Analysis creation failed, please try again (" + response.getHttpCode() + ")",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}