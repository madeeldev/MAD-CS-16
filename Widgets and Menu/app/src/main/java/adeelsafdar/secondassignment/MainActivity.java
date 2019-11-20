package adeelsafdar.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ContactAdapter mAdapter;

    // To store contacts list in an arrayList
    ArrayList<Contact> contactsArrayList = new ArrayList<>();

    private int listItemPositionForPopupMenu;
    // List of contacts
    Contact[] contactsList = {
            new Contact(R.drawable.contact2, "Usama Baig" , "03001234567", "email"),
            new Contact(R.drawable.contact2, "Nabeel Sajid" , "03000987654", "email"),
            new Contact(R.drawable.contact2, "Muhammad Faraz" , "03006546765", "email"),
            new Contact(R.drawable.contact2, "Muhammad Azhar" , "03008967542", "email"),
            new Contact(R.drawable.contact2, "M.Umair" , "03126745325", "ranaadeelofficial@gmail.com")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.movies_list);

        for(Contact m:contactsList)
            contactsArrayList.add(m);

        mAdapter = new ContactAdapter(this,contactsArrayList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadPopUpMenu(view, position);
            }
        });

    }

    private void loadPopUpMenu(View v, int listItemPosition)
    {
        listItemPositionForPopupMenu = listItemPosition;

        PopupMenu popup = new PopupMenu(MainActivity.this, v);
        popup.setGravity(Gravity.END);

        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        //creating intent
        final Intent intentForPhone = new Intent(this, Call.class);
        final Intent intentForMessage = new Intent(this, Message.class);
        final Intent intentForEmail = new Intent(this, Email.class);
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle().toString().equals(":: Phone")){
                    Contact m = (Contact) contactsArrayList.get(listItemPositionForPopupMenu);
                    String phone = m.getmContactNumber();
                    intentForPhone.putExtra("phoneNumber", phone);
                    startActivity(intentForPhone);

                }
                else if(item.getTitle().equals(":: Message")){
                    Contact m = (Contact) contactsArrayList.get(listItemPositionForPopupMenu);
                    String phone = m.getmContactNumber();
                    intentForMessage.putExtra("phoneNumber", phone);
                    startActivity(intentForMessage);
                }
                else if(item.getTitle().equals(":: Email")){
                    Contact m = (Contact) contactsArrayList.get(listItemPositionForPopupMenu);
                    String email = m.getmEmail();
                    intentForEmail.putExtra("userEmail", email);
                    startActivity(intentForEmail);
                }
                return true;
            }
        });

        popup.show();//showing popup menu
    }



    public void ShowPop(View v, int position)
    {
        loadPopUpMenu(v, position);
    }
}
