package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.optimix.handlers.CreateAnalysisHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

/**
 * Analysis fragmet extends fragment for showing and adding analysis.
 */
public class AnalysisFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.analysisUInput)
    EditText analysisUInput;
    @BindView(R.id.analysisXInput)
    EditText analysisXInput;
    @BindView(R.id.analysis_raw_amount_input)
    EditText analysis_raw_amount_input;
    @BindView(R.id.analysis_water_input)
    EditText analysis_water_input;
    @BindView(R.id.analysis_fat_input)
    EditText analysis_fat_input;
    @BindView(R.id.analysis_proteins_input)
    EditText analysis_proteins_input;

    @BindView(R.id.analysisRawName)
    TextView analysisRawName;

    @BindView(R.id.analysisXMessage)
    TextView analysisXMessage;

    @BindView(R.id.submitAnalysisButton)
    Button submitAnalysisButton;

    Raw desiredRaw;
    String analysisX;

    boolean error;
    boolean firstFocus;

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(?:0[1-9]|[1-2][0-9]|3[0-1])(?:0[1-9]|1[0-2])(?:20[0-9][0-9])";


    public AnalysisFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        ButterKnife.bind(this, view);
        error = false;

        analysisUInput.setOnFocusChangeListener(onUIFocus);
        analysisXInput.setOnFocusChangeListener(onUXFocus);
        submitAnalysisButton.setOnClickListener(onSubmit);

        return view;
    }


    View.OnFocusChangeListener onUXFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {

                analysisX = analysisXInput.getText().toString();
                pattern = Pattern.compile(DATE_PATTERN);
                matcher = pattern.matcher(analysisX);
                if (!matcher.matches()) {
                    analysisXMessage.setText("Pogrešan datum!");
                    error = true;
                } else {
                    analysisXMessage.setText("");
                    error = false;
                }
            }
        }

    };
    View.OnFocusChangeListener onUIFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            String analysisU = analysisUInput.getText().toString();
            desiredRaw = new Raw(analysisU);
            if (!hasFocus) {
                ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.all_raw_path),
                        ServiceCaller.HTTP_POST, null);
                new ServiceAsyncTask(rawFillHandler).execute(params);

            }
        }

    };

    SimpleResponseHandler rawFillHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                Type listType = new TypeToken<ArrayList<Raw>>() {
                }.getType();
                ArrayList<Raw> t = new Gson().fromJson(response.getJsonResponse(), listType);

                error = true;
                for (Raw raw : t) {
                    if (raw.getRawCode().equals(desiredRaw.getRawCode())) {
                        analysisRawName.setText(raw.getRawName());
                        desiredRaw = raw;
                        error = false;
                    }
                }
                if (error) {
                    analysisRawName.setText("Krivo unesena šifra sirovine!");
                    return false;
                } else {
                    return true;
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch raws", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };

    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try {
                Long analysisRawAmount = Long.parseLong(analysis_raw_amount_input.getText().toString());
                Double analysisWater = Double.parseDouble(analysis_water_input.getText().toString());
                Double analysisFat = Double.parseDouble(analysis_fat_input.getText().toString());
                Double analysisProteins = Double.parseDouble(analysis_proteins_input.getText().toString());

                if (analysisRawAmount == 0 || analysisWater == 0 || analysisFat == 0 || analysisProteins == 0 || analysisX.equals(null) || desiredRaw.equals(null)) {
                    error = true;
                }

                Analysis analysis = new Analysis(analysisWater, analysisFat, analysisProteins, analysisX, analysisRawAmount, desiredRaw);
                if (!error) {
                    CreateAnalysisHandler createAnalysisHandler = new CreateAnalysisHandler(getActivity(), analysis);

                    new ServiceAsyncTask(createAnalysisHandler).execute(new ServiceParams(
                            getString(hr.foi.air.optimix.webservice.R.string.analysis_createanalysis_path),
                            ServiceCaller.HTTP_POST, analysis));
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Krivo uneseni podaci!", Toast.LENGTH_LONG).show();

                }
            } catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }
    };
}
