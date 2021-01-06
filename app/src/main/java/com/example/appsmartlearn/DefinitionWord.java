package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DefinitionWord extends AppCompatActivity {
    Button btnBack;
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

        final TextView tvDefinition = (TextView) findViewById(R.id.tv_word_definition);
        final TextView tvEngWord = (TextView) findViewById(R.id.tv_eng_word);
        final TextView tvType = (TextView) findViewById(R.id.tv_word_type);
        String engWord = intent.getStringExtra("engWord");
        String def = intent.getStringExtra("meaning");
        String type = intent.getStringExtra("type");
        tvEngWord.setText(engWord);
        tvDefinition.setText(def);
        tvType.setText(type);

    }
}
