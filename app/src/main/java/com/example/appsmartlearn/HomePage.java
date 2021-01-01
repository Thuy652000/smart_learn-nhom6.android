package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private DrawerLayout drawer;
    private Button btn_normal_vocab, btn_img_vocab, btn_quiz;
    private NavigationView navigationView;
    private GridView catGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        drawer = findViewById(R.id.drawer_layout);
        btn_normal_vocab = findViewById(R.id.normal_vocab);
        btn_img_vocab = findViewById(R.id.img_vocab);
        btn_quiz =findViewById(R.id.quiz_vocab);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        catGrid = findViewById(R.id.catGridView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        btn_normal_vocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityNormalVocabList.class));
            }
        });

        btn_img_vocab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ActivityImgVocab.class));
            }
        });
        btn_quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        //updateNavHeader();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = currentUser.getUid();


        View headerView = navigationView.getHeaderView(0);
        final TextView tvUsername = headerView.findViewById(R.id.tv_nav_header_username);
        final TextView tvEmail = headerView.findViewById(R.id.tv_nav_header_email);
        //final TextView tvDoB = (TextView) findViewById(R.id.tv_date_of_birth);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model_User userProfile = snapshot.getValue(Model_User.class);

                if (userProfile != null) {
                    String username = userProfile.username;
                    String email = userProfile.email;

                    tvUsername.setText(username);
                    tvEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            HomePage.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile: {
                Intent intent = new Intent(HomePage.this, UserProfile.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_home: {

                break;
            }
            case R.id.nav_progress:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}