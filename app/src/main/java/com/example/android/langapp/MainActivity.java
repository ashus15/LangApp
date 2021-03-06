package com.example.android.langapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

                TextView num =findViewById(R.id.number_text_view);
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,NumberActivity.class);
                startActivity(i);
            }
        });

        TextView col =findViewById(R.id.color_text_view);
        col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ColorActivity.class);
                startActivity(i);
            }
        });

        TextView fam =findViewById(R.id.family_text_view);
        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(i);
            }
        });

        TextView phr =findViewById(R.id.phrase_text_view);
        phr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PhraseActivity.class);
                startActivity(i);
            }
        });


    }
}