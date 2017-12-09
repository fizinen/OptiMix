package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Material;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by erdel on 9.12.2017..
 */

public class MaterialAdapter  extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Material>{


    public MaterialAdapter(Context context, int resource, ArrayList<Material> items) {
        super(context, resource, items);
    }

    public static class ViewHolder {
        public TextView materialName;
        public TextView materialAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_material_of_recipe, parent, false);
                holder = new ViewHolder();
                holder.materialName = (TextView) vi.findViewById(R.id.recipe_detail_list_material_name);
                holder.materialAmount =(TextView) vi.findViewById(R.id.recipe_detail_list_material_amount);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Material current = getItems().get(position);
            holder.materialName.setText(current.getMaterialName());
            holder.materialAmount.setText( String.valueOf(current.getMaterialAmount()));

        } catch (Exception e) {
            Log.d("Error", "Couldn't create listing elements");
        }
        return vi;
    }
}
