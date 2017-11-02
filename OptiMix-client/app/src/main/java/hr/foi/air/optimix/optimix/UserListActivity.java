package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.OnClick;

/**
 * Created by Bogdan Erdelji on 31/10/2017.
 *
 * This is a class intended
 */

public class UserListActivity extends Fragment implements View.OnClickListener{

    FloatingActionButton buttonAddNewUsers;

    public UserListActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_user_list, container, false);
        buttonAddNewUsers = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddNewUsers);
        buttonAddNewUsers.setOnClickListener(this);
        return view;
    }

    @OnClick(R.id.floatingActionButtonAddNewUsers)
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
            case R.id.floatingActionButtonAddNewUsers: openNewUserCreationActivity();
                break;
            }
        }

}
