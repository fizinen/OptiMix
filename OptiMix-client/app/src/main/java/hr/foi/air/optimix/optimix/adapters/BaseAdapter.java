package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gloria Babić on 4.12.2017..
 */


/**
 * Abstract class that makes basic functions for all adapters in appliacation.
 * Makes constructor, gets count, gets item(s), gets position, gets itemId, gets Inflater and items in list.
 * @param <T>
 */
public abstract class BaseAdapter<T extends Serializable> extends ArrayAdapter<T> {

    private LayoutInflater inflater;
    private ArrayList<T> items;

    public BaseAdapter(Context context, int resource, ArrayList<T> items) {
        super(context, resource);
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getPosition(T item) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public ArrayList<T> getItems() {
        return items;
    }
}