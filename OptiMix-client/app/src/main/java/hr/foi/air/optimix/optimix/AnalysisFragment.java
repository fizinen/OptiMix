package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.sql.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.model.Analysis;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class AnalysisFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    @BindView(R.id.analysis_materialNumber_input)
    EditText analysis_materialNumber_input;
    @BindView(R.id.analysis_materialName_input)
    EditText analysis_materialName_input;
    @BindView(R.id.analysis_materialSeries_input)
    EditText analysis_materialSeries_input;
    @BindView(R.id.analysis_materialDateBefore_input)
    EditText analysis_materialDateBefore_input;
    @BindView(R.id.analysis_materialAmount_input)
    EditText analysis_materialAmount_input;

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
                    String materialNumber = analysis_materialNumber_input.getText().toString();
                    String materialName = analysis_materialName_input.getText().toString();
                    String materialSeries = analysis_materialSeries_input.getText().toString();
                    Date materialDateBefore = Date.valueOf(analysis_materialDateBefore_input.getText().toString());
                    double materialAmount = Double.valueOf(analysis_materialAmount_input.getText().toString());

                    //TODO

                    // Material material = new Material( materialNumber, materialName, materialSeries,materialDateBefore,materialAmount);

                    /*CreateRawHandler createRawHandler = new CreateRawHandler(CreateRawHandler.this, material);

                    new ServiceAsyncTask(createRawHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.analysis_createraw_path),
                    ServiceCaller.HTTP_POST, material));*/

                    break;
                case R.id.floatingActionButtonAddAnalysis:

                    double water = Double.valueOf(analysis_water_input.getText().toString());
                    double fat = Double.valueOf(analysis_fat_input.getText().toString());
                    double proteins = Double.valueOf(analysis_proteins_input.getText().toString());
                    //Material rawId;
                    //private List<AnalysisLog> analysisLog;

                    //TODO

                   // Analysis analysis = new Analysis(rawId, water, fat, proteins);

                    /*CreateRawHandler createRawHandler = new CreateRawHandler(CreateRawHandler.this, material);

                    new ServiceAsyncTask(createRawHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.analysis_createraw_path),
                    ServiceCaller.HTTP_POST, material));*/
                    break;

            }

        }


    }
}
