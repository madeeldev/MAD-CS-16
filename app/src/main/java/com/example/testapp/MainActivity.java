package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SendEmail(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void SendMessage(View view){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
    public void Call(View view){
        Intent intent = new Intent(this, Main4Activity.class);
        startActivity(intent);
    }
}
