package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
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

    @BindView(R.id.submitRecipeButton)
    Button submitButton;

    public CreateRecipeActivity(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
       // submitButton.setOnClickListener(onSubmit);
    }

    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

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
}
