package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DefinitionWord extends AppCompatActivity {
    Button btnBack, btnMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.definition_word);

        btnBack = (Button) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dictionary.class));
            }
        });

        Intent intent = getIntent();

        final TextView tvMean = (TextView) findViewById(R.id.tv_word_definition);
        final TextView tvMean2 = (TextView) findViewById(R.id.tv_word_definition2);
        final TextView tvEngWord = (TextView) findViewById(R.id.tv_eng_word);
        final TextView tvEngWord1 = (TextView) findViewById(R.id.tv_eng_word1);
        final TextView tvType = (TextView) findViewById(R.id.tv_word_type);
        final TextView tvEx = (TextView) findViewById(R.id.tv_ex);
        final TextView tvEx2 = (TextView) findViewById(R.id.tv_ex2);
        String engWord = intent.getStringExtra("engWord");
        String def = intent.getStringExtra("meaning");
        String def2 = intent.getStringExtra("meaning2");
        String type = intent.getStringExtra("type");
        String read = intent.getStringExtra("read");
        String ex = intent.getStringExtra("ex");
        String ex2 = intent.getStringExtra("ex2");
        tvEngWord.setText(engWord);
        tvEngWord1.setText(engWord + "  " + read);
        tvMean.setText("✪ " + def);
        if(def2 != null) {
            tvMean2.setText("✪ " + def2); } else {
            tvMean2.setText(" ");
        }
        tvType.setText(type);
        tvEx.setText("➥ " + ex);
        if(ex2 != null) {
        tvEx2.setText("➥ " + ex2); } else {
            tvEx2.setText(" ");
        }

    }
}
