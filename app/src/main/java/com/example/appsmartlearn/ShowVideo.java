package com.example.appsmartlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowVideo extends AppCompatActivity {
    Button btn_back;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);

        btn_back = (Button) findViewById(R.id.btn_back_notice);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        //databaseReference = database.getReference("video");

        recyclerView = findViewById(R.id.recyclerview_ShowVideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("video");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ModelVideo> options =
                new FirebaseRecyclerOptions.Builder<ModelVideo>().setQuery(databaseReference, ModelVideo.class).build();
        FirebaseRecyclerAdapter<ModelVideo, ViewHolderVideo> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelVideo, ViewHolderVideo>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolderVideo viewHolderVideo, int i, @NonNull ModelVideo modelVideo) {
                        viewHolderVideo.setExoPlayer(getApplication(), modelVideo.getName(), modelVideo.getVideourl());

                    }

                    @NonNull
                    @Override
                    public ViewHolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_video_item,parent, false);

                        return new ViewHolderVideo(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}