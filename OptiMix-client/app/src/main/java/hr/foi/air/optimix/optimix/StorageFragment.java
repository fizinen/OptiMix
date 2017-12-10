package hr.foi.air.optimix.optimix;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.model.Material;
import hr.foi.air.optimix.optimix.adapters.StorageAdapter;
import hr.foi.air.optimix.webservice.ServiceAsyncTask;
import hr.foi.air.optimix.webservice.ServiceCaller;
import hr.foi.air.optimix.webservice.ServiceParams;
import hr.foi.air.optimix.webservice.ServiceResponse;
import hr.foi.air.optimix.webservice.SimpleResponseHandler;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class StorageFragment extends android.support.v4.app.Fragment {

    ListView storage;

    public StorageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_storage_list, container, false);

        //dohvati zalihe

        storage = (ListView) view.findViewById(R.id.listViewStorage);

        ServiceParams params = new ServiceParams(
                getString(R.string.all_analysis_path),
                ServiceCaller.HTTP_GET, null);
        new ServiceAsyncTask(storageListHandler).execute(params);

        return view;
    }

    SimpleResponseHandler storageListHandler = new SimpleResponseHandler() {
        @Override
        public boolean handleResponse(ServiceResponse response) {
            if (response.getHttpCode() == 200) {

                Type listType = new TypeToken<ArrayList<Analysis>>() { }.getType();
                ArrayList<Analysis> t = new Gson().fromJson(response.getJsonResponse(), listType);


                storage.setAdapter(new StorageAdapter(getActivity().getApplicationContext(),
                        R.layout.fragment_storage_list, t));
                return true;
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to fetch storage", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    };
}
