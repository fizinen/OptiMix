package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Analysis;
import hr.foi.air.optimix.model.Raw;
import hr.foi.air.optimix.optimix.R;
import hr.foi.air.optimix.optimix.StorageFragment;

/**
 * Created by Gloria Babić on 7.12.2017..
 */

public class StorageAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Analysis> {

    public StorageAdapter(Context context, int resource, ArrayList<Analysis> items) {
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
                vi = getInflater().inflate(R.layout.list_item_storage, null);
                holder = new ViewHolder();
                holder.rawName = (TextView) vi.findViewById(R.id.raw_name);
                holder.rawUx = (TextView) vi.findViewById(R.id.raw_ux);
                holder.rawWater = (TextView) vi.findViewById(R.id.raw_water);
                holder.rawFat = (TextView) vi.findViewById(R.id.raw_fat);
                holder.rawProteins = (TextView) vi.findViewById(R.id.raw_proteins);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Analysis current = getItems().get(position);

            holder.rawWater.setText(String.valueOf(current.getWater()));
            holder.rawFat.setText(String.valueOf(current.getFat()));
            holder.rawProteins.setText(String.valueOf(current.getProteins()));

            Raw currentRaw = current.getRawId();
            if (currentRaw != null){
                holder.rawName.setText(currentRaw.getRawName());
                holder.rawUx.setText(String.valueOf((currentRaw.getRawCode()) + "-" + String.valueOf(current.getExpirationDate())));
                holder.rawQuantity.setText(String.valueOf(current.getAnalysisRawMass()));

            }
            else {
                holder.rawName.setText("Nije dostupno");
                holder.rawUx.setText("Nije dostupno");

            }

        } catch (Exception e) {
            Log.d("storage greška nezz", "ups");
        }
        return vi;
    }
}