package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Calculation;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by Lenovo on 20.1.2018..
 */

/**
 * Extends Base adapeter.
 * Makes view for xml of fragment/activity to show Calculation app uses.
 */
public class CalculationAdapter  extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Calculation>{

    Context context;
    ArrayList<Calculation> calculationArrayList;

    public CalculationAdapter(Context context, int resource, ArrayList<Calculation> items){
        super(context, resource, items);
        this.context = context;
        this.calculationArrayList = items;
    }

    public static class ViewHolder {
        public TextView calculationFullAmount;
        public TextView recipeName;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final CalculationAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_calculation, null);
                holder = new CalculationAdapter.ViewHolder();
                holder.recipeName = (TextView) vi.findViewById(R.id.calculation_recipe_name);
                holder.calculationFullAmount = (TextView) vi.findViewById(R.id.calculation_full_amount);

                vi.setTag(holder);
            } else {
                holder = (CalculationAdapter.ViewHolder) vi.getTag();
            }

            Calculation current = getItems().get(position);
            holder.recipeName.setText(current.getRecipeId().getRecipeName());
            holder.calculationFullAmount.setText(String.valueOf(current.getCalculationFullAmount()));


        } catch (Exception e) {
            Log.d("greškica", "ups");
        }
        return vi;
    }

}
