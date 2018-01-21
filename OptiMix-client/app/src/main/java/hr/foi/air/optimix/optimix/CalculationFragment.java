package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.adapters.SpinnerRecipeAdapter;
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

    ListView recipes;
    ArrayList<Recipe> recipeList;
    boolean error;

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

            Recipe recipe = (Recipe) chooseRecipe.getSelectedItem();

            Double calcAmount = Double.parseDouble(calculationAmount.getText().toString());

            Calculation calculation = new Calculation(calcAmount, recipe);

            CreateCalculationHandler createCalculationHandler = new CreateCalculationHandler(getActivity(), calculation);

            new ServiceAsyncTask(createCalculationHandler).execute(new ServiceParams(
                    getString(hr.foi.air.optimix.webservice.R.string.calculation_create_path),
                    ServiceCaller.HTTP_POST, calculation));


        }
    };


}
