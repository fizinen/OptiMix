package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Recipe;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by Lenovo on 20.1.2018..
 */

/**
 * Extends Base adapeter.
 * Makes view for xml of fragment/activity to show first Spinner for Recipes app uses.
 * Makes dropdown view for xml of fragment/activity to show all remaining Spinner(s) for Recipes app uses
 */
public class SpinnerRecipeAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Recipe> {
    Context context;
    ArrayList<Recipe> recipeArrayList;

    public SpinnerRecipeAdapter(Context context, int resource, ArrayList<Recipe> items) {
        super(context, resource, items);
        this.context = context;
        this.recipeArrayList = items;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = getInflater().inflate(R.layout.recipe_info, parent, false);
        TextView recipename = (TextView) convertView.findViewById(R.id.Arecipe_name);

        recipename.setText(recipeArrayList.get(position).getRecipeName().toString());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView text = new TextView(context);
        final float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (16 * scale + 0.5f);
        text.setPadding(px, px, px, px);

        text.setText(recipeArrayList.get(position).getRecipeName());
        return text;
    }

}
