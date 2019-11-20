package adeelsafdar.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Call extends AppCompatActivity {

    EditText mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        mPhoneNumber = (EditText) findViewById(R.id.textView_call);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("phoneNumber");
        mPhoneNumber.setText(message);
    }

    public void DialNumber(View view){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phone_number = "tel:" + mPhoneNumber.getText().toString();
        intent.setData(Uri.parse(phone_number));
        startActivity(intent);
    }
}
