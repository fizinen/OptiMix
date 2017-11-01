package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public UserListActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.tab_user_list, container, false);
    }

    @OnClick(R.id.floatingActionButtonAddNewUsers)
    public void openNewUserCreationActivity() {
        Activity parentActivity = getActivity();
        Intent intent = new Intent(parentActivity, CreateUserActivity.class);
        ((FragmentIntentInterface)parentActivity).startIntentFromFragment(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButtonAddNewUsers: openNewUserCreationActivity();
                break;
            }
        }

}
