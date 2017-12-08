package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class StorageAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Raw>{

    public StorageAdapter(Context context, int resource, ArrayList<Raw> items) {
        super(context, resource, items);
    }


    public static class ViewHolder {
        public TextView rawName;
        public TextView rawUx;
        public TextView rawQuantity;
        public TextView rawWater;
        public TextView rawFat;
        public TextView rawProteins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final StorageAdapter.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item, null);
                holder = new StorageAdapter.ViewHolder();
                holder.rawName = (TextView) vi.findViewById(R.id.raw_name);
                holder.rawUx = (TextView) vi.findViewById(R.id.raw_ux);
                holder.rawQuantity = (TextView) vi.findViewById(R.id.raw_quantity);
                holder.rawWater = (TextView) vi.findViewById(R.id.raw_water);
                holder.rawFat = (TextView) vi.findViewById(R.id.raw_fat);
                holder.rawProteins = (TextView) vi.findViewById(R.id.raw_proteins);

                vi.setTag(holder);
            } else {
                holder = (StorageAdapter.ViewHolder) vi.getTag();
            }

            /*Material current = getItems().get(position);
            holder.rawName.setText(current.getMaterialName());
                holder.rawUx.setText(current.getMaterialUX());
                holder.rawQuantity.setText(current.getMaterialQuantity());
                holder.rawWater.setText(current.getMaterialWater());
                holder.rawFat.setText(current.getMaterialFat());
                holder.rawProteins.setText(current.getMaterialProteins());

             */


        } catch (Exception e) {
            Log.d("greškica", "ups");
        }
        return vi;
    }
}