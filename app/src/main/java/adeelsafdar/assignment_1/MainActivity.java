package adeelsafdar.assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PhoneCall(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:03001234567"));
        startActivity(intent);
    }

    public void SendSms(View view){
        Intent intent = new Intent(this, sendSmsAct.class);
        startActivity(intent);
    }

    public void SendEmail(View view){
        Intent intent = new Intent(this, EmailSender.class);
        intent.setType("text/html");
        intent.putExtra("senderEmail", "emailaddress@gmail.com");
        startActivity(intent);
    }


}
