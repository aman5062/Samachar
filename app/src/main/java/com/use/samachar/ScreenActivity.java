package com.use.samachar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends AppCompatActivity {
    ImageView menu_icon;
    ImageView search_icon;
    Dialog dialog;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<Article> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        getSupportActionBar().hide();
        menu_icon = findViewById(R.id.menu_icon);
        search_icon = findViewById(R.id.search_button);
        // Receive the data from MainActivity
        Intent intent = getIntent();
        articles = intent.getParcelableArrayListExtra("articles");

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(articles);
        recyclerView.setAdapter(newsAdapter);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(ScreenActivity.this);
                dialog.setContentView(R.layout.menu);
                dialog.setCancelable(true);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_Samachar;
                dialog.getWindow().setBackgroundDrawableResource(android.R.drawable.screen_background_light_transparent);

                dialog.show();
            }
        });
        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScreenActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}