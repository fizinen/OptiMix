package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.CalculationAnalysis;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by Lenovo on 22.1.2018..
 */

public class CalculationAnalysisAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<CalculationAnalysis> {

    Context context;
    ArrayList<CalculationAnalysis> calculationAnalysisArrayList;

    public CalculationAnalysisAdapter(Context context, int resource, ArrayList<CalculationAnalysis> items) {
        super(context, resource, items);
        this.context = context;
        this.calculationAnalysisArrayList = items;
    }

    public static class ViewHolder {
        public TextView calculationAnalysisRawName;
        public TextView calculationAnalysisRawAmount;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_calculation, null);
                holder = new ViewHolder();
                holder.calculationAnalysisRawName = (TextView) vi.findViewById(R.id.calculation_made_raw_name);
                holder.calculationAnalysisRawAmount = (TextView) vi.findViewById(R.id.calculationAnalysis_full_amount);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            CalculationAnalysis current = getItems().get(position);
            holder.calculationAnalysisRawName.setText(current.getAnalysisCalculationId().getRawId().getRawName());
            holder.calculationAnalysisRawAmount.setText(String.valueOf(current.getAmountForCalculation()));


        } catch (Exception e) {
            Log.d("greškica", "ups");
        }
        return vi;
    }

}