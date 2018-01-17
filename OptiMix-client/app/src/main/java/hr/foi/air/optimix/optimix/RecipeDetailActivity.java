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
import hr.foi.air.optimix.optimix.adapters.RawAdapter;
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

        if (recipeId != -1) {
            ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.recipe_path) + recipeId,
                    ServiceCaller.HTTP_POST, null);
            new ServiceAsyncTask(recipeHandler).execute(params);
        }
    }

    SimpleResponseHandler recipeHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                 Recipe recipe = new Gson().fromJson(response.getJsonResponse(), Recipe.class);

                ArrayList<Raw> recipeRawsArray = new ArrayList<Raw>(recipe.getListOfRecipeRaws());

                recipeName.setText(recipe.getRecipeName());

                recipeRaws.setAdapter(new RawAdapter(RecipeDetailActivity.this,
                        R.layout.activity_recipe_detail, recipeRawsArray));
                return true;
            } else {
                Toast.makeText(RecipeDetailActivity.this, "Failed to fetch storage", Toast.LENGTH_LONG).show();
                return false;
            }

        }
    };
}

