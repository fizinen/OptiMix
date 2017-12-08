package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Material;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by erdel on 8.12.2017..
 */

public class MaterialsAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Material>{
    Context context;
    ArrayList<Material> materialArrayList;
    public MaterialsAdapter(Context context, int resource, ArrayList<Material> items) {
        super(context, resource, items);
        this.context = context;
        this.materialArrayList = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView text = new TextView(context);
        text.setText(materialArrayList.get(position).getMaterialName());
        text.setTextColor(Color.DKGRAY);

        return text;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView text = new TextView(context);
        text.setTextColor(Color.parseColor("#939393"));
        text.setBackgroundColor(Color.parseColor("#EEEEEE"));

        // Add padding to the TextView, scaled to device
        final float scale = context.getResources().getDisplayMetrics().density;
        int px = (int) (16 * scale + 0.5f);
        text.setPadding(px, px, px, px);

        text.setText(materialArrayList.get(position).getMaterialName());
        return text;
    }
}
