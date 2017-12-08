package hr.foi.air.optimix.optimix;

import android.app.Fragment;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class AnalysisFragment extends android.support.v4.app.Fragment implements View.OnClickListener {


    FloatingActionButton buttonAddNewAnalysis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis, container, false);
        buttonAddNewAnalysis = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddAnalysis);
        buttonAddNewAnalysis.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

    }
}
