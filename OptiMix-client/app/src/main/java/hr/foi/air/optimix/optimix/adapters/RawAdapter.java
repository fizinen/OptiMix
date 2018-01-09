package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by erdel on 9.12.2017..
 */

public class RawAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Raw>{


    public RawAdapter(Context context, int resource, ArrayList<Raw> items) {
        super(context, resource, items);
    }

    public static class ViewHolder {
        public TextView rawName;
        public TextView rawAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_raw_of_recipe, parent, false);
                holder = new ViewHolder();
                holder.rawName = (TextView) vi.findViewById(R.id.recipe_detail_list_raw_name);
                holder.rawAmount =(TextView) vi.findViewById(R.id.recipe_detail_list_raw_amount);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Raw current = getItems().get(position);
            holder.rawName.setText(current.getRawName());
            holder.rawAmount.setText( String.valueOf(current.getRawMass()));

        } catch (Exception e) {
            Log.d("Error", "Couldn't create listing elements");
        }
        return vi;
    }
}
