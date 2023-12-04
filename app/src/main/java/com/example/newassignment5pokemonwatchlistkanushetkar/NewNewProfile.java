package com.example.newassignment5pokemonwatchlistkanushetkar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NewNewProfile extends AppCompatActivity {
    TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_new_profile);
        TV = findViewById(R.id.profileTV);
    }
}