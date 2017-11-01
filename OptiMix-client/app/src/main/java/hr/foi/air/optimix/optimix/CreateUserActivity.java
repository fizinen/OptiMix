package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.handlers.CreateUserHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;

/**
 * Created by Bogdan Erdelji on 31/10/2017.
 */

public class CreateUserActivity extends Fragment {


    @BindView(R.id.userName)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.userNameLayout)
    TextInputLayout usernameLayout;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;
    @BindView(R.id.submitButton)
    Button submitButton;
    Input passwordInput;
    List<Input> inputs;



    public CreateUserActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_create_user, container, false);

        ButterKnife.bind(this, view); /*


        passwordInput = new Input(password, Input.PASSWORD_PATTERN, getString(R.string.password_error));

        inputs = Arrays.asList(
                new Input(username, Input.TEXT_MAIN_PATTERN, getString(R.string.username_error)),
                passwordInput
        );

        View.OnClickListener onSubmit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if(Input.validate(inputs)){

                    Person person = new Person(usernameValue,passwordValue);
                    CreateUserActivity instanceFragment =
                            (CreateUserActivity)getFragmentManager().findFragmentById(R.id.activity_create_user);

                    CreateUserHandler createUserHandler = new CreateUserHandler(  , person);

                    new ServiceAsyncTask(createUserHandler).execute(new ServiceParams(
                            getString(hr.foi.air.optimix.webservice.R.string.person_createuser_path),
                            ServiceCaller.HTTP_POST, person));
                }
            }
        };

        submitButton.setOnClickListener(onSubmit); */

        return view;
    }
}
