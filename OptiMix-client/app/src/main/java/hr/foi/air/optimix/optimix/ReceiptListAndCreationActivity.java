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

import butterknife.OnClick;

/**
 * Created by erdel on 27.11.2017..
 */

public class ReceiptListAndCreationActivity extends Fragment implements View.OnClickListener {

    FloatingActionButton buttonAddNewRecipes;

    public ReceiptListAndCreationActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes_overview, container, false);
        buttonAddNewRecipes = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddNewRecipe);
        buttonAddNewRecipes.setOnClickListener(this);
        return view;
    }




    @OnClick(R.id.floatingActionButtonAddNewRecipe)
    public void openNewUserCreationActivity() {
        Log.d("klik", "kliknuo si me");
        Activity parentActivity = getActivity();
        Intent intent = new Intent(parentActivity, CreateUserActivity.class);
        ((FragmentIntentInterface)parentActivity).startIntentFromFragment(intent);
    }

    @Override
    public void onClick(View v) {
        Log.d("klik2", "kliknuo si me2");
        switch (v.getId()){
            case R.id.floatingActionButtonAddNewRecipe: openNewUserCreationActivity();
                break;
        }
    }

}
