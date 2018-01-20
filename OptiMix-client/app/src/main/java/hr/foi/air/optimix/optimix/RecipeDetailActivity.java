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
import java.util.List;

import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.model.RecipeRaws;
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

    long recipeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detalji recepta");

        setContentView(R.layout.activity_recipe_detail);

        recipeName = (TextView) findViewById(R.id.recipe_detail_recipe_name);
        recipeRaws = (ListView) findViewById(R.id.recipe_detail_raw_list);

        Intent intent = getIntent();
        recipeId = intent.getLongExtra("idRecipe", -1);
/*
        if (recipeId != -1) {
            ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.recipe_path) + recipeId,
                    ServiceCaller.HTTP_POST, null);
            new ServiceAsyncTask(recipeHandler).execute(params);
        }
*/
        if (recipeId != -1) {
            ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.raws_for_recipe_path) + recipeId,
                    ServiceCaller.HTTP_POST, null);
            new ServiceAsyncTask(recipeRawsHandler).execute(params);
        }
    }

    SimpleResponseHandler recipeRawsHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                List<RecipeRaws> listar = new Gson().fromJson(response.getJsonResponse(), new TypeToken<List<RecipeRaws>>(){}.getType());

                ArrayList<RecipeRaws> alista = new ArrayList<>(listar);

                try{
                    recipeName.setText(alista.get(0).getRawRecipeId().getRecipeName());

                    recipeRaws.setAdapter(new RecipeRawsAdapter(RecipeDetailActivity.this,
                            R.layout.activity_recipe_detail, alista));
                    }

                catch (Exception E){
                    Toast.makeText(RecipeDetailActivity.this, "Recept je prazan", Toast.LENGTH_LONG).show();
                }
                return true;
            } else {
                Toast.makeText(RecipeDetailActivity.this, "Failed to fetch storage", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };
    /*
    SimpleResponseHandler recipeHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Recipe recipe = new Gson().fromJson(response.getJsonResponse(), Recipe.class);

                ArrayList<RecipeRaws> recipeRawsArray = new ArrayList<RecipeRaws>(recipe.getRecipeRaws());

                ArrayList<Raw> rec = new ArrayList<>();

                for(int i = 0; i < recipeRawsArray.size(); i++){
                    rec.add(recipeRawsArray.get(i).getRecipeRawId());
                }

                recipeName.setText(recipe.getRecipeName());

                recipeRaws.setAdapter(new RawsAdapter(RecipeDetailActivity.this,
                        R.layout.activity_recipe_detail, rec));
						
                return true;
            } else {
                Toast.makeText(RecipeDetailActivity.this, "Failed to fetch storage", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };
    */
}

