package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dictionary extends AppCompatActivity {

    Button btnBack;
    DatabaseReference ref;
    ArrayList<Word> list;
    RecyclerView recyclerView;
    private WordAdapter.myWordHolder mAdapter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary);

        btnBack = (Button) findViewById(R.id.btn_back_dic);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });
        ref = FirebaseDatabase.getInstance().getReference().child("dictionary");
        recyclerView = findViewById(R.id.rvWord);
        searchView = findViewById(R.id.searchView);
    }
    @Override
    protected void onStart() {

        super.onStart();
        if(ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        list = new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            list.add(ds.getValue(Word.class));
                        }
                        WordAdapter wordAdapter = new WordAdapter(list);
                        //recyclerView.setAdapter(wordAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        if(searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }
    private void search(String str) {
        ArrayList<Word> myList = new ArrayList<>();
        for(Word object : list) {
            if(object.getEngWord().toLowerCase().contains(str.toLowerCase())) {
                myList.add(object);
            }
        }
        WordAdapter wordAdapter = new WordAdapter(myList);
        recyclerView.setAdapter(wordAdapter);
    }
}