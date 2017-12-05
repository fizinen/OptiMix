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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.handlers.CreateUserHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;

/**
 * Created by erdel on 27.11.2017..
 */

public class CreateRecipeActivity extends AppCompatActivity {

    @BindView(R.id.submit_recipe_button) Button submitButton;
    @BindView(R.id.add_new_material_button) Button newMaterialButton;
    //Layout that we copy
    @BindView(R.id.material_addition_layout) ViewGroup newMaterialAdditionLayout;
    // Insert point
    @BindView(R.id.recipe_details_layout) ViewGroup recipeDetailsLayout;

    private int materialAddedCounter;

    public CreateRecipeActivity(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        ButterKnife.bind(this);
        materialAddedCounter = 0;
        newMaterialButton.setOnClickListener(onMaterialAdded);
        submitButton.setOnClickListener(onSubmit);
    }
    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("klik","kliknut gumb submit");
/*
            //This part is the same as in CreateUserActivity - it has to be edited and done in such
            // way so that it works for recipes
            String nameValue = personName.getText().toString();
            String surnameValue = personSurname.getText().toString();
            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();

            if(Input.validate(inputs)){

                Person person = new Person(nameValue, surnameValue, usernameValue,passwordValue);

                CreateUserHandler createUserHandler = new CreateUserHandler( CreateUserActivity.this , person);

                new ServiceAsyncTask(createUserHandler).execute(new ServiceParams(
                        getString(hr.foi.air.optimix.webservice.R.string.person_createuser_path),
                        ServiceCaller.HTTP_POST, person));
            }*/
        }
    };
    View.OnClickListener onMaterialAdded = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            // Here we have to add dynamical generation of Layouts such as is @id/material_addition_layout

            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewGroup parent = (ViewGroup) findViewById(R.id.insertion_point);
            View view = inflater.inflate(R.layout.layout_material_addition, null);
            parent.addView(view, materialAddedCounter);
            materialAddedCounter++;
        }
    };
}
