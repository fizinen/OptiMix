package hr.foi.air.optimix.optimix;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.model.RecipeRaws;
import hr.foi.air.optimix.optimix.adapters.RawsAdapter;
import hr.foi.air.optimix.optimix.handlers.CreateRecipeHandler;
import hr.foi.air.optimix.optimix.handlers.CreateRecipeRawsHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by erdel on 27.11.2017..
 */

/**
 * Create recipe activity extends AppCompatActivity for creating recipes.
 */
public class CreateRecipeActivity extends AppCompatActivity {

    @BindView(R.id.submit_recipe_button)
    Button submitButton;
    @BindView(R.id.add_new_raw_button)
    Button newRawButton;
    @BindView(R.id.recipe_details_layout)
    ViewGroup recipeDetailsLayout;
    @BindView(R.id.recipe_name)
    EditText recipeName;
    @BindView(R.id.error_text)
    TextView errorText;

    private int rawAddedCounter;
    private int maximalNumberOfAddedSpinners;
    ArrayList<Spinner> generatedRawsSpinner;
    ArrayList<EditText> generatedRawAmount;

    Recipe createdRecipe;
    ArrayList<Raw> rawName;
    ArrayList<Raw> usedRaws;
    ArrayList<Double> rawAmount;


    public CreateRecipeActivity() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        ButterKnife.bind(this);
        rawAddedCounter = 0;
        maximalNumberOfAddedSpinners = 1;
        newRawButton.setOnClickListener(onRawAdded);
        submitButton.setOnClickListener(onSubmit);
        createdRecipe = new Recipe();
        generatedRawsSpinner = new ArrayList<Spinner>();
        generatedRawAmount = new ArrayList<EditText>();
        rawName = new ArrayList<Raw>();
        rawAmount = new ArrayList<Double>();
        usedRaws = new ArrayList<>();

        setTitle("Dodavanje recepata");
    }

    View.OnClickListener onRawAdded = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (rawAddedCounter > 0) {
                usedRaws.add((Raw) generatedRawsSpinner.get(rawAddedCounter - 1).getSelectedItem());
            }
            //Addition of spinner elements
            if (rawAddedCounter != (maximalNumberOfAddedSpinners)) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup parent = (ViewGroup) findViewById(R.id.insertion_point);
                View view = inflater.inflate(R.layout.layout_raw_addition, null);
                parent.addView(view, rawAddedCounter);
                rawAddedCounter++;
                Spinner spinner = (Spinner) view.findViewById(R.id.generated_raw_selection_spinner);
                generatedRawsSpinner.add(spinner);
                EditText editText = (EditText) view.findViewById(R.id.generated_raw_amount);
                generatedRawAmount.add(editText);
                //Data insertion into spinners (lists of materials)
                ServiceParams params = new ServiceParams(
                        getString(R.string.all_raw_path),
                        ServiceCaller.HTTP_GET, null);
                new ServiceAsyncTask(rawListHandler).execute(params);

            } else {
                Toast.makeText(getApplicationContext(), "No new materials to add to the recipe", Toast.LENGTH_LONG).show();

            }

        }
    };

    SimpleResponseHandler rawListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                Type listType = new TypeToken<ArrayList<Raw>>() {
                }.getType();
                ArrayList<Raw> t = new Gson().fromJson(response.getJsonResponse(), listType);
                maximalNumberOfAddedSpinners = t.size();
                ArrayList<Integer> index = new ArrayList<>();
                for (Raw r : t) {
                    for (Raw ru: usedRaws) {
                        if (r.getRawCode().equals(ru.getRawCode())){
                            index.add(t.indexOf(r));
                        }
                    }
                }
                int g = 0;
                for (int i:index) {
                    t.remove(i-g);
                    g++;
                }

                generatedRawsSpinner.get(rawAddedCounter - 1).setAdapter(new RawsAdapter(getApplicationContext(), R.layout.activity_create_recipe, t));
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch the materials", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };

    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String nameValue = recipeName.getText().toString();
            double sum = 0;
            for (Spinner s : generatedRawsSpinner) {
                rawName.add((Raw) s.getSelectedItem());
            }

            for (EditText e : generatedRawAmount) {
                try {
                    rawAmount.add(Double.parseDouble(e.getText().toString()));
                    sum += Double.parseDouble(e.getText().toString());
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_LONG).show();
                }
            }

            if (sum <= 100 && sum != 0) {

                Recipe recipe = new Recipe(nameValue);

                CreateRecipeHandler createRecipeHandler = new CreateRecipeHandler(CreateRecipeActivity.this, recipe);

                new ServiceAsyncTask(createRecipeHandler).execute(new ServiceParams(
                        getString(hr.foi.air.optimix.webservice.R.string.recipe_createrecipe_path),
                        ServiceCaller.HTTP_POST, recipe));

                ServiceParams params = new ServiceParams(
                        getString(R.string.all_recipes_path),
                        ServiceCaller.HTTP_GET, null);
                new ServiceAsyncTask(recipeListHandler).execute(params);
            } else {
                if (sum > 100) {
                    errorText.setText(R.string.error_message);
                }
                if (sum == 0) {
                    errorText.setText(R.string.error_message_zero);
                }

                rawName.clear();
                rawAmount.clear();
            }
        }
    };

    SimpleResponseHandler recipeListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                Type listType = new TypeToken<ArrayList<Recipe>>() {
                }.getType();
                ArrayList<Recipe> t = new Gson().fromJson(response.getJsonResponse(), listType);

                for (Recipe r : t) {
                    if (r.getRecipeName().equals(recipeName.getText().toString())) {
                        createdRecipe = r;

                        for (int i = 0; i < rawName.size(); i++) {

                            RecipeRaws recipeRaws = new RecipeRaws(createdRecipe.getIdRecipe(), createdRecipe, rawName.get(i), rawAmount.get(i));

                            CreateRecipeRawsHandler createRecipeRawsHandler = new CreateRecipeRawsHandler(CreateRecipeActivity.this, recipeRaws);

                            new ServiceAsyncTask(createRecipeRawsHandler).execute(new ServiceParams(
                                    getString(hr.foi.air.optimix.webservice.R.string.reciperaws_createreciperaws_path),
                                    ServiceCaller.HTTP_POST, recipeRaws));
                        }
                        return true;
                    }
                }
                return true;

            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch recipes", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };
}
