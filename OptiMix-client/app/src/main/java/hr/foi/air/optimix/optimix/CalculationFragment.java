package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.model.Calculation;
import hr.foi.air.optimix.model.CalculationAnalysis;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.model.RecipeRaws;
import hr.foi.air.optimix.optimix.adapters.CalculationAnalysisAdapter;
import hr.foi.air.optimix.optimix.adapters.CalculationToDocumentAdapter;
import hr.foi.air.optimix.optimix.adapters.SpinnerRecipeAdapter;
import hr.foi.air.optimix.optimix.handlers.CreateCalculationAnalysisHandler;
import hr.foi.air.optimix.optimix.handlers.CreateCalculationHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;


/**
 * Created by Lenovo on 20.1.2018..
 */


/**
 * Calculation fragmet extends fragment for showing and adding calculations.
 */
public class CalculationFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.recipe_spinner)
    Spinner chooseRecipe;
    @BindView(R.id.preview_of_calculations)
    Button calculationPreview;
    @BindView(R.id.recipe_amount)
    EditText calculationAmount;
    @BindView(R.id.calculation_made_name)
    TextView calculationRecipeMadeName;
    @BindView(R.id.calculation_full_amount)
    TextView calculationFullAmount;
    @BindView(R.id.layout_recipe_calculation_view)
    LinearLayout layoutRecipeCalculationView;
    @BindView(R.id.document_create_pdf)
    Button documentCreatePdf;
    @BindView(R.id.document_print_modular)
    Button documentPrint;

    boolean error = false;
    boolean uneseno = false;
    ArrayList<RecipeRaws> listOfRecipeRaws;
    ArrayList<Analysis> listOfAllAnalysis;
    ArrayList<CalculationAnalysis> listOfCalculationAnalysis;
    Double calcAmount;
    Calculation calculation;
    Recipe recipe;
    CalculationAnalysis calculationAnalysis;
    List<CalculationAnalysis> listOfAnalysis;
    View view;
    ListView listViewForPreview;
    DocumentButtonManager documentButtonManager;

    ArrayList<CalculationAnalysis> documentCalculationAnalysisList;


    public CalculationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calculation, container, false);

        ButterKnife.bind(this, view);
        documentButtonManager = new DocumentButtonManager();
        listViewForPreview = (ListView) view.findViewById(R.id.listViewRecipes);

        ServiceParams params = new ServiceParams(
                getString(R.string.all_recipes_path),
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(recipeListHandler).execute(params);
        calculationPreview.setOnClickListener(onCalculationPreview);
        documentPrint.setOnClickListener(onCreateDocumentDemand);
        documentCreatePdf.setOnClickListener(onCreateDocumentDemand);

        return view;
    }


    SimpleResponseHandler recipeListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Recipe>>() {
                }.getType();
                ArrayList<Recipe> recipeList = new Gson().fromJson(response.getJsonResponse(), listType);

                SpinnerRecipeAdapter adapter = new SpinnerRecipeAdapter(getContext(), R.layout.fragment_calculation, recipeList);

                chooseRecipe.setAdapter(adapter);

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch recipes", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };


    SimpleResponseHandler recipeRawsListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                List<RecipeRaws> listar = new Gson().fromJson(response.getJsonResponse(), new TypeToken<List<RecipeRaws>>() {
                }.getType());

                listOfRecipeRaws = new ArrayList<>(listar);

                return true;

            } else {
                Toast.makeText(getActivity(), "Failed to fetch reciperaws", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };


    SimpleResponseHandler analysisListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Analysis>>() {
                }.getType();
                ArrayList<Analysis> t = new Gson().fromJson(response.getJsonResponse(), listType);

                listOfAllAnalysis = new ArrayList<>(t);

                ServiceParams params = new ServiceParams(
                        getString(R.string.all_calculation_path),
                        ServiceCaller.HTTP_GET, null);
                new ServiceAsyncTask(calculationListHandler).execute(params);

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch analysis", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };

    SimpleResponseHandler calculationListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Calculation>>() {
                }.getType();
                ArrayList<Calculation> t = new Gson().fromJson(response.getJsonResponse(), listType);

                for (Calculation c : t) {
                    if (c.getCalculationFullAmount() == (calculation.getCalculationFullAmount()) && c.getRecipeId().getIdRecipe() == (calculation.getRecipeId().getIdRecipe())) {
                        for (RecipeRaws rr : listOfRecipeRaws) {
                            uneseno = false;
                            for (Analysis a : listOfAllAnalysis) {
                                if (rr.getRecipeRawId().getIdRaw() == a.getRawId().getIdRaw() && rr.getRawAmount() > 0 && uneseno == false) {
                                    double amountForCalculation = (rr.getRawAmount() / 100) * calcAmount;
                                    if (amountForCalculation < a.getAnalysisRawMass()) {
                                        uneseno = true;
                                        calculationAnalysis = new CalculationAnalysis(c.getRecipeId().getIdRecipe(), c, a, amountForCalculation);

                                        CreateCalculationAnalysisHandler createCalculationAnalysisHandler = new CreateCalculationAnalysisHandler(getActivity(), calculationAnalysis);

                                        new ServiceAsyncTask(createCalculationAnalysisHandler).execute(new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.calculation_analysis_create_path),
                                                ServiceCaller.HTTP_POST, calculationAnalysis));
                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), "Premalo analizirane sirovine za recept", Toast.LENGTH_LONG).show();
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                calculationPreview.performClick();
                Toast.makeText(getActivity().getApplicationContext(), "Izračun gotov", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch calculation", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };

    View.OnClickListener onCalculationPreview = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            recipe = (Recipe) chooseRecipe.getSelectedItem();
            layoutRecipeCalculationView.setVisibility(View.VISIBLE);

            if (recipe.getIdRecipe() != -1) {
                ServiceParams params = new ServiceParams(
                        getString(R.string.all_calculationanalysis_path),
                        ServiceCaller.HTTP_GET, null);
                new ServiceAsyncTask(calculationAnalysisHandler).execute(params);
            }

        }
    };

    View.OnClickListener onCreateDocumentDemand = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
                Button buttonTemporaryPressed = (Button) v;
            try {
                CalculationToDocumentAdapter calculationToDocumentAdapter = new CalculationToDocumentAdapter(documentCalculationAnalysisList);
                documentButtonManager.StartModule(buttonTemporaryPressed,
                        calculationToDocumentAdapter.getName(),
                        calculationToDocumentAdapter.getContent(),
                        getActivity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    SimpleResponseHandler calculationAnalysisHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                recipe = (Recipe) chooseRecipe.getSelectedItem();
                calcAmount = Double.parseDouble(calculationAmount.getText().toString());

                List<CalculationAnalysis> calculationAnalysises = new Gson().fromJson(response.getJsonResponse(), new TypeToken<List<CalculationAnalysis>>() {
                }.getType());

                listOfCalculationAnalysis = new ArrayList<>(calculationAnalysises);

                ArrayList<CalculationAnalysis> t = new ArrayList<>();

                for (CalculationAnalysis c : listOfCalculationAnalysis) {
                    if (c.getCalculationId() == recipe.getIdRecipe() && c.getCalculationAnalysisId().getCalculationFullAmount() == calcAmount) {
                        t.add(c);
                    }
                }


                if(t.size() > 0){
                    documentCalculationAnalysisList = t;

                    calculationRecipeMadeName.setText(recipe.getRecipeName());
                    calculationFullAmount.setText(String.valueOf(calcAmount));
                    //Ovo je dodano
                    listOfAnalysis = t;
                    //
                    listViewForPreview.setAdapter(new CalculationAnalysisAdapter(getActivity().getApplicationContext(),
                            R.layout.fragment_calculation, t));



                    return true;
                } else {
                    recipe = (Recipe) chooseRecipe.getSelectedItem();
                    calcAmount = Double.parseDouble(calculationAmount.getText().toString());

                    calculation = new Calculation(calcAmount, recipe);

                    CreateCalculationHandler createCalculationHandler = new CreateCalculationHandler(getActivity(), calculation);

                    new ServiceAsyncTask(createCalculationHandler).execute(new ServiceParams(
                            getString(hr.foi.air.optimix.webservice.R.string.calculation_create_path),
                            ServiceCaller.HTTP_POST, calculation));

                    if (recipe.getIdRecipe() != -1) {
                        ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.raws_for_recipe_path) + recipe.getIdRecipe(),
                                ServiceCaller.HTTP_POST, null);
                        new ServiceAsyncTask(recipeRawsListHandler).execute(params);
                    }

                    if (recipe.getIdRecipe() != -1) {
                        ServiceParams params = new ServiceParams(
                                getString(R.string.all_analysis_path),
                                ServiceCaller.HTTP_GET, null);
                        new ServiceAsyncTask(analysisListHandler).execute(params);
                    }

                    return true;
                }

            } else {
                Toast.makeText(getActivity(), "Failed to fetch reciperaws", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };

}
