package com.use.samachar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,screen.class);
                startActivity(intent);
                finish();
            }
        }, 3500);


        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

