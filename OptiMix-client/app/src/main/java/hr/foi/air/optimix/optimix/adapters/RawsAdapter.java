package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import hr.foi.air.optimix.model.Raw;

/**
 * Created by erdel on 8.12.2017..
 */

/**
 * Extends Base adapeter.
 * Makes view for xml of fragment/activity to show Raws app uses.
 */
public class RawsAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Raw>{
    Context context;
    ArrayList<Raw> rawArrayList;
    public RawsAdapter(Context context, int resource, ArrayList<Raw> items) {
        super(context, resource, items);
        this.context = context;
        this.rawArrayList = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView text = new TextView(context);
        text.setText(rawArrayList.get(position).getRawName());
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

        text.setText(rawArrayList.get(position).getRawName());
        return text;
    }
}
