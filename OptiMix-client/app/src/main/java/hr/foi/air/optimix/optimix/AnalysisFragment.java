package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.model.Raw;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class AnalysisFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    @BindView(R.id.analysis_rawNumber_input)
    EditText analysis_rawNumber_input;
    @BindView(R.id.analysis_rawName_input)
    EditText analysis_rawName_input;
    @BindView(R.id.analysis_rawSeries_input)
    EditText analysis_rawSeries_input;
    @BindView(R.id.analysis_rawDateBefore_input)
    EditText analysis_rawDateBefore_input;
    @BindView(R.id.analysis_rawAmount_input)
    EditText analysis_rawAmount_input;

    @BindView(R.id.floatingActionButtonAddRaw)
    FloatingActionButton buttonAddNewRaw;


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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);

        ButterKnife.bind(this, view);

        //buttonAddNewAnalysis = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddAnalysis);
        buttonAddNewAnalysis.setOnClickListener(this);
        buttonAddNewRaw.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        {
            int id = v.getId();
            switch (id) {
                case R.id.floatingActionButtonAddRaw:
                    String rawNumber = analysis_rawNumber_input.getText().toString();
                    String rawName = analysis_rawName_input.getText().toString();
                    String rawSeries = analysis_rawSeries_input.getText().toString();
                    String rawDateBefore = analysis_rawDateBefore_input.getText().toString();
                    long rawAmount = Long.valueOf(analysis_rawAmount_input.getText().toString());


                   Raw material = new Raw( rawNumber, rawName, rawSeries,rawDateBefore,rawAmount);

                    //CreateRawHandler createRawHandler = new CreateRawHandler(CreateRawHandler.this, material);

                    /*new ServiceAsyncTask(createRawHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.analysis_createraw_path),
                    ServiceCaller.HTTP_POST, material)); */

                    break;
                case R.id.floatingActionButtonAddAnalysis:

                    double water = Double.valueOf(analysis_water_input.getText().toString());
                    double fat = Double.valueOf(analysis_fat_input.getText().toString());
                    double proteins = Double.valueOf(analysis_proteins_input.getText().toString());
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
}
