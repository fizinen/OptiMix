package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.optimix.adapters.RawsAdapter;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class AnalysisFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    /*
    @BindView(R.id.analysis_rawNumber_input)
    EditText analysis_rawNumber_input;
    @BindView(R.id.analysis_rawName_input)
    EditText analysis_rawName_input;

    @BindView(R.id.analysis_rawSeries_input)
    EditText analysis_rawSeries_input;

    @BindView(R.id.analysis_rawAmount_input)
    EditText analysis_rawAmount_input;

    @BindView(R.id.floatingActionButtonAddRaw)
    FloatingActionButton buttonAddNewRaw;
    */

    @BindView(R.id.analysis_raw_date_input)
    EditText analysis_raw_date_input;
    @BindView(R.id.analysis_ux_input)
    EditText analysis_ux_input;
    @BindView(R.id.analysis_fat_input)
    EditText analysis_fat_input;
    @BindView(R.id.analysis_quantity_input)
    EditText analysis_quantity_input;
    @BindView(R.id.analysis_proteins_input)
    EditText analysis_proteins_input;
    @BindView(R.id.analysis_water_input)
    EditText analysis_water_input;


    @BindView(R.id.floatingActionButtonAddAnalysis)
    FloatingActionButton buttonAddNewAnalysis;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String[] analysis_raw_name_fills = {"svinjetina", "teletina", "tele2", "konje", "rowk"};

    ListView raws;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        raws = (ListView) view.findViewById(R.id.listViewRaws);

        ServiceParams params = new ServiceParams(
                getString(R.string.all_raw_path),
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(rawsListHandler).execute(params);

        AutoCompleteTextView acTextView = (AutoCompleteTextView) view.findViewById(R.id.analysis_raw_name_input);
        ArrayAdapter<String> adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, analysis_raw_name_fills);

        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);

        ButterKnife.bind(this, view);

        //buttonAddNewAnalysis = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddAnalysis);
        buttonAddNewAnalysis.setOnClickListener(this);
        //buttonAddNewRaw.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        {
            int id = v.getId();
            switch (id) {
                /*
                case R.id.floatingActionButtonAddRaw:
                    String rawNumber = analysis_rawNumber_input.getText().toString();
                    String rawName = analysis_rawName_input.getText().toString();

                   Raw material = new Raw(rawNumber, rawName);

                    //CreateRawHandler createRawHandler = new CreateRawHandler(CreateRawHandler.this, material);

                    /*new ServiceAsyncTask(createRawHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.analysis_createraw_path),
                    ServiceCaller.HTTP_POST, material)); */ /*

                    break;
                */
                case R.id.floatingActionButtonAddAnalysis:

                    double water = Double.valueOf(analysis_water_input.getText().toString());
                    double fat = Double.valueOf(analysis_fat_input.getText().toString());
                    double proteins = Double.valueOf(analysis_proteins_input.getText().toString());
                    String rawDateBefore = String.valueOf(analysis_raw_date_input.getText().toString());
                    long rawAmount = Long.valueOf(analysis_quantity_input.getText().toString());
                    //Raw rawId;
                    //private List<AnalysisLog> analysisLog;

                    //TODO

                    // Analysis analysis = new Analysis(rawId, water, fat, proteins);

                    /*CreateRawHandler createRawHandler = new CreateRawHandler(CreateRawHandler.this, raw);

                    new ServiceAsyncTask(createRawHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.analysis_createraw_path),
                    ServiceCaller.HTTP_POST, material));*/
                    break;

            }

        }

    }


    SimpleResponseHandler rawsListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Raw>>() {
                }.getType();
                ArrayList<Raw> t = new Gson().fromJson(response.getJsonResponse(), listType);

                //setViewLayout(R.layout.fragment_team_history);
                raws.setAdapter(new RawsAdapter(getActivity().getApplicationContext(),
                        R.layout.fragment_analysis, t));

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch raws", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };

}
