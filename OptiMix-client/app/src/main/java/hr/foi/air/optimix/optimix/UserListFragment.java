package hr.foi.air.optimix.optimix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.OnClick;
import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.adapters.UserAdapter;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by Bogdan Erdelji on 31/10/2017.
 *
 * This is a class intended
 */

public class UserListFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton buttonAddNewUsers;
    //@BindView(R.id.listViewUsers)
    ListView users;

    public UserListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        //dohvati sve osobe

        users = (ListView) view.findViewById(R.id.listViewUsers);

        ServiceParams params = new ServiceParams(
                getString( R.string.all_persons_path) ,
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(usersListHandler).execute(params);

        buttonAddNewUsers = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddNewUsers);
        buttonAddNewUsers.setOnClickListener(this);
        return view;
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

    SimpleResponseHandler usersListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if(response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Person>>() { }.getType();
                ArrayList<Person> t = new Gson().fromJson(response.getJsonResponse(), listType);

                //setViewLayout(R.layout.fragment_team_history);
                users.setAdapter(new UserAdapter(getActivity().getApplicationContext(),
                            R.layout.fragment_user_list, t));

                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch users", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };

}
