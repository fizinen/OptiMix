package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.optimix.core.Input;
import hr.foi.air.optimix.core.SessionManager;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.handlers.LoginHandler;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;

public class LoginActivity extends AppCompatActivity {


    List<Input> inputs;

    @BindView(R.id.TextUsername) EditText username;
    @BindView(R.id.TextPassword) EditText password;
    @BindView(R.id.TextUsernameLayout) TextInputLayout usernameLayout;
    @BindView(R.id.TextPasswordLayout) TextInputLayout passwordLayout;
    @BindView(R.id.ButtonLogin) Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //binding
        ButterKnife.bind(this);

        Log.d("","username" + username.getText().toString());
        Log.d("","pass" + password.getText().toString());

      /*  inputs = Arrays.asList(
                new Input(username, Input.TEXT_MAIN_PATTERN, getString(R.string.username_error)),
                new Input(password, Input.PASSWORD_PATTERN, getString(R.string.password_error))
        ); */

        if(SessionManager.getInstance(this)
                .retrieveSession(SessionManager.PERSON_INFO_KEY, Person.class) != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
        logIn.setOnClickListener(onlogin);
        
    }
    View.OnClickListener onlogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO: upisati u log

            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();

          // if(Input.validate(inputs)) {

                Person credentials = new Person(usernameValue, passwordValue);

            Log.d("","person jajaja" + credentials.toString());
                LoginHandler loginHandler = new LoginHandler(LoginActivity.this);
            Log.d("","loginhandlerhah jajaja" + loginHandler.toString());
                ServiceParams params = new ServiceParams(getString(hr.foi.air.optimix.webservice.R.string.person_login_path), ServiceCaller.HTTP_POST, credentials);
                try {
                    Log.d("hh", "GGevo meeeeeeeeeeeeeee");
                    new ServiceAsyncTask(loginHandler).execute(params);
                } catch(Exception e) {
                    Log.d("hh", "evo meeeeeeeeeeeeeee");
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.internet_error), Toast.LENGTH_LONG).show();
                }
          //  }
        }
    };



}
