package com.example.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Welcome2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);

        //time splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //chuyen man
                Intent intent = new Intent(Welcome2.this, Wecome.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}