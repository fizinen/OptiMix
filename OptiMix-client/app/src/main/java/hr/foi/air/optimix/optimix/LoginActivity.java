package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    List<ScriptGroup.Input> inputs;

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
        
    }
    @OnClick(R.id.ButtonLogin)
    public void mainButtonClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
