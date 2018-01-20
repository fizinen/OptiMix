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
import hr.foi.air.optimix.model.RecipeRaws;
import hr.foi.air.optimix.optimix.R;


/**
 * Created by Lenovo on 20.1.2018..
 */

public class RecipeRawsAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<RecipeRaws>{
    Context context;
    ArrayList<RecipeRaws> recipeRawsArrayList;
    public RecipeRawsAdapter(Context context, int resource, ArrayList<RecipeRaws> items) {
        super(context, resource, items);
        this.context = context;
        this.recipeRawsArrayList = items;
    }

    public static class ViewHolder {
        public TextView rawName;
        public TextView rawAmount;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_raw_of_recipe, parent, false);
                holder = new ViewHolder();
                holder.rawName = (TextView) vi.findViewById(R.id.recipe_detail_list_raw_name);
                holder.rawAmount = (TextView) vi.findViewById(R.id.recipe_detail_list_raw_amount);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            RecipeRaws current = getItems().get(position);
            holder.rawName.setText(current.getRecipeRawId().getRawName());
            holder.rawAmount.setText(String.valueOf(current.getRawAmount()));

        } catch (Exception e) {
            Log.d("Error", "Couldn't create listing elements");
        }
        return vi;
    }
}