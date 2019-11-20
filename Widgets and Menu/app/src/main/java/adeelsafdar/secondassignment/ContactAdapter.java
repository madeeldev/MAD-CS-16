package adeelsafdar.secondassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    private List<Contact> contactsList = new ArrayList<>();

    public ContactAdapter(Context context, ArrayList<Contact> list) {
        super(context, 0 , list);
        mContext = context;
        contactsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Contact currentContact = contactsList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        image.setImageResource(currentContact.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentContact.getmName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText(currentContact.getmContactNumber());

        TextView email = (TextView) listItem.findViewById(R.id.textView_email);
        email.setText(currentContact.getmEmail());

        return listItem;
    }
}