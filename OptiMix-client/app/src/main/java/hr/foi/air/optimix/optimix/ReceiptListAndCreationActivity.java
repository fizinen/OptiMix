package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.adapters.RecipeAdapter;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by erdel on 27.11.2017..
 */

public class ReceiptListAndCreationActivity extends Fragment implements View.OnClickListener {

    FloatingActionButton buttonAddNewRecipes;

    ListView recipes;


    public ReceiptListAndCreationActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_overview, container, false);

        //dohvati recepte

        recipes = (ListView) view.findViewById(R.id.listViewRecipes);

        ServiceParams params = new ServiceParams(
                getString(R.string.all_recipes_path),
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(recipeListHandler).execute(params);

        buttonAddNewRecipes = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddNewRecipe);
        buttonAddNewRecipes.setOnClickListener(this);

        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //We have to get the id of the chosen recipe so first we need an object
                Recipe theChosenRecipe = (Recipe) parent.getItemAtPosition(position);
                long idOfRecipe = theChosenRecipe.getIdRecipe();

                Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
                intent.putExtra("idRecipe", idOfRecipe);
                startActivity(intent);
            }
        });
        return view;
    }

    @OnClick(R.id.floatingActionButtonAddNewRecipe)
    public void openNewRecipeCreationActivity() {
        Activity parentActivity = getActivity();
        Intent intent = new Intent(parentActivity, CreateRecipeActivity.class);
        ((FragmentIntentInterface) parentActivity).startIntentFromFragment(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButtonAddNewRecipe:
                openNewRecipeCreationActivity();
                break;
        }
    }

    SimpleResponseHandler recipeListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {
                Type listType = new TypeToken<ArrayList<Recipe>>() {
                }.getType();
                ArrayList<Recipe> t = new Gson().fromJson(response.getJsonResponse(), listType);
                recipes.setAdapter(new RecipeAdapter(getActivity().getApplicationContext(),
                        R.layout.fragment_recipes_overview, t));

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch recipes", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };

}
