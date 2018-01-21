package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.model.Calculation;
import hr.foi.air.optimix.model.CalculationAnalysis;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.model.RecipeRaws;
import hr.foi.air.optimix.optimix.adapters.RecipeRawsAdapter;
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

public class CalculationFragment extends android.support.v4.app.Fragment {

    @BindView(R.id.recipe_spinner)
    Spinner chooseRecipe;
    @BindView(R.id.make_calculation_button)
    Button calculateRecipe;
    @BindView(R.id.recipe_amount)
    EditText calculationAmount;
    
    boolean error = false;
    ArrayList<RecipeRaws> listOfRecipeRaws;
    ArrayList<Analysis> listOfAllAnalysis;
    Double calcAmount;
    Calculation calculation;
    Recipe recipe;
    CalculationAnalysis calculationAnalysis;

    public CalculationFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculation, container, false);

        ButterKnife.bind(this, view);

        ServiceParams params = new ServiceParams(
                getString(R.string.all_recipes_path),
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(recipeListHandler).execute(params);

        calculateRecipe.setOnClickListener(onCalculate);

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

    View.OnClickListener onCalculate  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

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

        }
    };



    SimpleResponseHandler recipeRawsListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                List<RecipeRaws> listar = new Gson().fromJson(response.getJsonResponse(), new TypeToken<List<RecipeRaws>>(){}.getType());

                listOfRecipeRaws = new ArrayList<>(listar);

                //Toast.makeText(getActivity(), "Dohvaćen recept", Toast.LENGTH_LONG).show();
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

                //Toast.makeText(getActivity().getApplicationContext(), "Dohvaćene analize", Toast.LENGTH_LONG).show();

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

                for(Calculation c : t){
                    if(c.getCalculationFullAmount() == (calculation.getCalculationFullAmount()) && c.getRecipeId().getIdRecipe() == (calculation.getRecipeId().getIdRecipe())){
                        for (RecipeRaws rr : listOfRecipeRaws){
                            for (Analysis a : listOfAllAnalysis){
                                if (rr.getRecipeRawId().getIdRaw() == a.getRawId().getIdRaw()){
                                    double amountForCalculation = (rr.getRawAmount()/1000)*calcAmount;
                                    if(amountForCalculation < a.getAnalysisRawMass()){

                                        calculationAnalysis = new CalculationAnalysis(c.getIdCalculation(), c, a, amountForCalculation);

                                        CreateCalculationAnalysisHandler createCalculationAnalysisHandler = new CreateCalculationAnalysisHandler(getActivity(), calculationAnalysis);

                                        new ServiceAsyncTask(createCalculationAnalysisHandler).execute(new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.calculation_analysis_create_path),
                                                ServiceCaller.HTTP_POST, calculationAnalysis));


                                        Toast.makeText(getActivity().getApplicationContext(), "Izračun gotov", Toast.LENGTH_LONG).show();

                                    }
                                    else{
                                        Toast.makeText(getActivity().getApplicationContext(), "Premalo analizirane sirovine za recept", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch calculation", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };

}
