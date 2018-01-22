package hr.foi.air.optimix.optimix.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hr.foi.air.optimix.model.Person;
import hr.foi.air.optimix.optimix.R;

/**
 * Created by Gloria Babić on 2.12.2017..
 */

/**
 * Extends Base adapeter.
 * Makes view for xml of fragment/activity to show Users (persons) app uses.
 */
public class UserAdapter extends hr.foi.air.optimix.optimix.adapters.BaseAdapter<Person>{

    public UserAdapter(Context context, int resource, ArrayList<Person> items) {
        super(context, resource, items);
    }


    public static class ViewHolder {
        public TextView personName;
        public TextView personSurname;
        public TextView personAuthority;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = getInflater().inflate(R.layout.list_item_user, null);
                holder = new ViewHolder();
                holder.personName = (TextView) vi.findViewById(R.id.person_name);
                holder.personSurname = (TextView) vi.findViewById(R.id.person_surname);
                holder.personAuthority = (TextView) vi.findViewById(R.id.person_authority);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Person current = getItems().get(position);
            holder.personName.setText(current.getName());
            holder.personAuthority.setText(current.getAuthorityString());
            holder.personSurname.setText(current.getSurname());


        } catch (Exception e) {
            Log.d("greškica", "ups");
        }
        return vi;
    }
}
