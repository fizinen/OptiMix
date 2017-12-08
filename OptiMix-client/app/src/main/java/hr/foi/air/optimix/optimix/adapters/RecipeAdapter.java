package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by erdel on 8.12.2017..
 */

public class RecipeAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Recipe> {

    public RecipeAdapter(Context context, int resource, ArrayList<Recipe> items) {
        super(context, resource, items);
    }

    public static class ViewHolder {
        public TextView recipeName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_recipe, parent, false);
                holder = new ViewHolder();
                holder.recipeName = (TextView) vi.findViewById(R.id.recipe_listing_recipe_name);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Recipe current = getItems().get(position);
            holder.recipeName.setText(current.getRecipeName());

        } catch (Exception e) {
            Log.d("Error", "Couldn't create listing elements");
        }
        return vi;
    }
}
