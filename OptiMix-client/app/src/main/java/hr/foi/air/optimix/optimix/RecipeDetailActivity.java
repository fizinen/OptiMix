package hr.foi.air.optimix.optimix;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.adapters.RawsAdapter;
import hr.foi.air.optimix.optimix.adapters.RecipeRawsAdapter;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;


/**
 * Created by erdel on 9.12.2017..
 */

public class RecipeDetailActivity extends AppCompatActivity {
    ListView recipeRaws;
    TextView recipeName;
    TextView recipeCoefficient;

    long recipeId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detalji recepta");

        setContentView(R.layout.activity_recipe_detail);

        recipeName = (TextView) findViewById(R.id.recipe_detail_recipe_name);
        recipeCoefficient = (TextView) findViewById(R.id.recipe_detail_recipe_coefficient);
        recipeRaws = (ListView) findViewById(R.id.recipe_detail_raw_list);

        Intent intent = getIntent();
        recipeId = intent.getLongExtra("idRecipe", -1);

        if (recipeId != -1) {
            // Here we will use dummy data but we need a webservice that returns recipe by id
            // the whole thing with recipeHandler and get response etc.

            //THIS HERE NEEDS TO BE EDITED TO FIT WEBSERVICE ARGUMENT NEEDS - recipeId has to be passed on
            ServiceParams params = new ServiceParams(
                    getString( R.string.all_recipes_path) ,
                    ServiceCaller.HTTP_GET, null);
            new ServiceAsyncTask(recipeHandler).execute(params);
/*
            //Testdata
            Recipe recipeObject = new Recipe();
            Raw newRaw1 = new Raw(10, "Svinjetina");
            Raw newRaw2 = new Raw(11, "Teletina");
            List<Raw> rawList = new ArrayList<Raw>();
            rawList.add(0, newRaw1);
            rawList.add(1, newRaw2);
            recipeObject.setEvaporationCoefficient(0.04);
            recipeObject.setIdRecipe(5);
            recipeObject.setRecipeName("Hrenovke");
            recipeObject.setListOfRecipeRaws(rawList);
            //End testdata

            recipeName.setText(recipeObject.getRecipeName());
            recipeCoefficient.setText(recipeObject.getEvaporationCoefficient().toString());

            ArrayList<Raw> rawArrayList = (ArrayList<Raw>) recipeObject.getListOfRecipeRaws();
            //Here we have to set adapter which we need to implement to show the arrayList <Raw>
            recipeRaws.setAdapter(new RecipeRawsAdapter(RecipeDetailActivity.this, R.layout.activity_recipe_detail, rawArrayList));
            */
        }
    }
    SimpleResponseHandler recipeHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if(response.getHttpCode() == 200) {

                Type objectType = new TypeToken<ArrayList<Recipe>>() { }.getType();
                ArrayList<Recipe> temporaryRecipeList = new Gson().fromJson(response.getJsonResponse(), objectType);
                Recipe temporaryRecipeObject = new Recipe();

                //Because our WS returns us a list of recipes we need to find the right one
                //TemporaryRecipeObject then gets it's value and is being used as source of
                //information about that recipe
                for(int i = 0; i < temporaryRecipeList.size();i++){
                    Recipe loopRecipeObject = temporaryRecipeList.get(i);
                    if (loopRecipeObject.getIdRecipe() == recipeId){
                        temporaryRecipeObject = loopRecipeObject;
                    }
                }

                //This part here is just to make sure that app doesn't crash when "bad recipes" are loaded.
                if(temporaryRecipeObject.getRecipeName()!=null){
                    recipeName.setText(temporaryRecipeObject.getRecipeName());
                }

                if (temporaryRecipeObject.getEvaporationCoefficient()!=null){
                    recipeCoefficient.setText(temporaryRecipeObject.getEvaporationCoefficient().toString());
                }

                ArrayList<Raw> rawArrayList = (ArrayList<Raw>) temporaryRecipeObject.getListOfRecipeRaws();
                if (rawArrayList != null){
                    recipeRaws.setAdapter(new RecipeRawsAdapter(RecipeDetailActivity.this, R.layout.activity_recipe_detail, rawArrayList));
                }
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Failed to fetch this recipe", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };
}

