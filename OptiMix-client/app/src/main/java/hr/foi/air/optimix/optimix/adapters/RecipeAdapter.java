package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.model.RecipeRaws;
import hr.foi.air.optimix.optimix.R;
import hr.foi.air.optimix.optimix.RecipeDetailActivity;

import static android.R.id.list;

/**
 * Created by erdel on 8.12.2017..
 */

/**
 * Extends Base adapeter.
 * Makes view for xml of fragment/activity to show Recipes app uses.
 */
public class RecipeAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Recipe> {

    Context context;
    public ListView recipeRaws;

    public RecipeAdapter(Context context, int resource, ArrayList<Recipe> items) {
        super(context, resource, items);
        this.context = context;
    }

    public static class ViewHolder {
        public TextView recipeName;
        public ListView recipeRaws;
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
                holder.recipeRaws = (ListView) vi.findViewById(R.id.recipe_detail_raw_list);
                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Recipe current = getItems().get(position);
            holder.recipeName.setText(current.getRecipeName());

            ArrayList<Raw> rec = new ArrayList<>();

            recipeRaws.setAdapter(new RawsAdapter(context, R.layout.activity_recipe_detail, rec));


        } catch (Exception e) {
            Log.d("Error", "Couldn't create listing elements");
        }
        return vi;
    }

}
