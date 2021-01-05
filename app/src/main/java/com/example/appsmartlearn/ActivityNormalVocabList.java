package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityNormalVocabList extends AppCompatActivity {
    private RecyclerView mRecycleView;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_vocab_list);
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview_Lesson);

        btn_back = (Button) findViewById(R.id.btn_back_notice);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        new FirebaseNormalVocab().readNormalVocab(new FirebaseNormalVocab.DataStatus() {
            @Override
            public void DataIsLoaded(List<ModelNormalVocab> vocabs, List<String> keys) {
                new NormalVocabRecycleView().setConfig(mRecycleView, ActivityNormalVocabList.this, vocabs, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }

        });

    }

}
