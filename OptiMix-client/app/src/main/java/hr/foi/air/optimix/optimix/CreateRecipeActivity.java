package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Material;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.adapters.MaterialsAdapter;
import hr.foi.air.optimix.optimix.handlers.CreateRecipeHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by erdel on 27.11.2017..
 */

public class CreateRecipeActivity extends AppCompatActivity {

    @BindView(R.id.submit_recipe_button) Button submitButton;
    @BindView(R.id.add_new_material_button) Button newMaterialButton;
    @BindView(R.id.recipe_details_layout) ViewGroup recipeDetailsLayout;
    @BindView(R.id.recipe_name)
    EditText recipename;

    private int materialAddedCounter;
    private int maximalNumberOfAddedSpinners;
    private Spinner generatedMaterialsSpinner;

    public CreateRecipeActivity(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        ButterKnife.bind(this);
        materialAddedCounter = 0;
        maximalNumberOfAddedSpinners = 1;
        newMaterialButton.setOnClickListener(onMaterialAdded);
        submitButton.setOnClickListener(onSubmit);

        setTitle("Dodavanje recepata");
    }
    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("klik","kliknut gumb submit");

            String nameValue = recipename.getText().toString();

            Recipe recipe = new Recipe(nameValue);

            CreateRecipeHandler createRecipeHandler = new CreateRecipeHandler( CreateRecipeActivity.this , recipe);

            new ServiceAsyncTask(createRecipeHandler).execute(new ServiceParams(
                getString(hr.foi.air.optimix.webservice.R.string.recipe_createrecipe_path),
                ServiceCaller.HTTP_POST, recipe));


        }
    };
    View.OnClickListener onMaterialAdded = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //Addition of spinner elements
            if(materialAddedCounter != (maximalNumberOfAddedSpinners)) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup parent = (ViewGroup) findViewById(R.id.insertion_point);
                View view = inflater.inflate(R.layout.layout_material_addition, null);
                parent.addView(view, materialAddedCounter);
                materialAddedCounter++;
                generatedMaterialsSpinner = (Spinner) view.findViewById(R.id.generated_material_selection_spinner);
            }else {
                Toast.makeText(getApplicationContext(), "No new materials to add to the recipe", Toast.LENGTH_LONG).show();
            }


            //Data insertion into spinners (lists of materials)
            ServiceParams params = new ServiceParams(
                    getString( R.string.all_raw_path) ,
                    ServiceCaller.HTTP_GET, null);
            new ServiceAsyncTask(materialListHandler).execute(params);

        }
    };

    SimpleResponseHandler materialListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if(response.getHttpCode() == 200) {
                Type listType = new TypeToken<ArrayList<Material>>() { }.getType();
                ArrayList<Material> t = new Gson().fromJson(response.getJsonResponse(), listType);
                maximalNumberOfAddedSpinners = t.size() ;
                generatedMaterialsSpinner.setAdapter(new MaterialsAdapter(getApplicationContext(), R.layout.activity_create_recipe, t));
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch the materials", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };
}
