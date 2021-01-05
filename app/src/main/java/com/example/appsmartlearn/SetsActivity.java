package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SetsActivity extends AppCompatActivity {
        private GridView sets_grid;
        public static int category_id;
        //public List<String> catlist;
       // private FirebaseFirestore firestore;
       private FirebaseDatabase database;
       private DatabaseReference mReference;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sets);

            Toolbar toolbar = findViewById(R.id.set_toolbar);
            setSupportActionBar(toolbar);


            String title = getIntent().getStringExtra("CATEGORY");
             category_id=getIntent().getIntExtra("CATEGORY_ID",1);
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            sets_grid = findViewById(R.id.sets_gridview);


            SetsAdapter adapter = new SetsAdapter(6);
            sets_grid.setAdapter(adapter);

        }


        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == android.R.id.home)
            {
                SetsActivity.this.finish();
            }
            return super.onOptionsItemSelected(item);
        }
}
