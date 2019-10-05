package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmailSender extends AppCompatActivity {
    private TextView mToTexView;
    private TextView mSubjectTextView;
    private TextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sender);

        mToTexView = findViewById(R.id.mToEditText);
        Intent in = getIntent();
        mToTexView.setText(in.getExtras().getString("senderEmail"));
    }
    public void mSendEmail(View view){
        // get text of all text views
        mToTexView = findViewById(R.id.mToEditText);
        mSubjectTextView = findViewById(R.id.mSubjectEditText);
        mMessageTextView = findViewById(R.id.mMessageEditText);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mToTexView.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, mSubjectTextView.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, mMessageTextView.getText().toString());
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
