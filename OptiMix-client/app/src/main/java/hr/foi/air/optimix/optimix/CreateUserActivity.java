package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.generators.PasswordGenerator;
import hr.foi.air.optimix.optimix.generators.UserNameGenerator;
import hr.foi.air.optimix.optimix.handlers.CreateUserHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;

/**
 * Created by Bogdan Erdelji on 31/10/2017.
 */

public class CreateUserActivity extends AppCompatActivity{


    @BindView(R.id.userName)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.userNameLayout)
    TextInputLayout usernameLayout;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.personName)
    EditText personName;
    @BindView(R.id.personSurname)
    EditText personSurname;
    @BindView(R.id.personNameLayout)
    TextInputLayout personNameLayout;
    @BindView(R.id.personSurnameLayout)
    TextInputLayout personSurnameLayout;
    @BindView(R.id.submitButton)
    Button submitButton;
    @BindView(R.id.radioAuthorityLevel)
    RadioGroup radioAuthorityLevel;
    Input passwordInput;
    List<Input> inputs;
    RadioButton checkedButton;




    public CreateUserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        ButterKnife.bind(this);

        setTitle("Dodavanje korisnika");

        passwordInput = new Input(password, Input.PASSWORD_PATTERN, getString(R.string.password_error));

        inputs = Arrays.asList(
                new Input(username, Input.TEXT_MAIN_PATTERN, getString(R.string.username_error)),
                passwordInput
        );
        submitButton.setOnClickListener(onSubmit);


    }
    View.OnClickListener onSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            long authorityLevel;

            String nameValue = personName.getText().toString();
            String surnameValue = personSurname.getText().toString();
            UserNameGenerator userNameGenerator = new UserNameGenerator(nameValue, surnameValue);
            //String usernameValue = username.getText().toString();
            String usernameValue = userNameGenerator.generateUsername();
            //String passwordValue = password.getText().toString();
            String passwordValue = PasswordGenerator.generate(8);

            checkedButton = (RadioButton) findViewById(radioAuthorityLevel.getCheckedRadioButtonId());

            if(checkedButton.getTag() == "teh"){
                authorityLevel = 1;
            }

            else {
                authorityLevel = 2;
            }


            username.setText(usernameValue);
            password.setText(passwordValue);



            if(Input.validate(inputs)){

                Person person = new Person(nameValue, surnameValue, usernameValue  ,passwordValue, authorityLevel);

                CreateUserHandler createUserHandler = new CreateUserHandler( CreateUserActivity.this , person);

                new ServiceAsyncTask(createUserHandler).execute(new ServiceParams(
                        getString(hr.foi.air.optimix.webservice.R.string.person_createuser_path),
                        ServiceCaller.HTTP_POST, person));
            }
        }
    };

/*
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_create_user, container, false);

    }*/
}
