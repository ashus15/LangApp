package com.example.android.langapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PhraseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PhraseFragment())
                .commit();
    }
}