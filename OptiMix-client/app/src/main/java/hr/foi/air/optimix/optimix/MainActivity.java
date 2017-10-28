package hr.foi.air.optimix.optimix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @OnClick(R.id.button)
    public void mainButtonClick(View view){
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    // event handler
    View.OnClickListener imageButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Hello Android!", Toast.LENGTH_SHORT).show();
        }
    };
}
