package com.example.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asm.ui.home.Login.Login;

public class Wecome extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wecome);

        //time splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //chuyen man
                Intent intent = new Intent(Wecome.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}