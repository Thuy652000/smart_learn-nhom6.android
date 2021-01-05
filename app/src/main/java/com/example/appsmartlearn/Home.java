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

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    private NavigationView navigationView;

    private DrawerLayout drawer;
    private GridView catGrid;
    private int setNo;
    private Button backhomepage;

    private FirebaseDatabase database;
    private DatabaseReference mReference;

    // private FirebaseFirestore firestore;

    public static List<String> catlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // backhomepage =findViewById(R.id.backhomePage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        catGrid = findViewById(R.id.catGridView);

        new Thread() {
            public void run() {
                loadData();
            }
        }.start();


//        backhomepage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(Home.this,HomePage.class);
//                Home.this.startActivity(intent);
//               Home.this.finish();
//            }
//        });


    }

    private void loadData()
    {       database=FirebaseDatabase.getInstance();
            catlist.clear();

        mReference= database.getReference("Category").child("CA");
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot catS :snapshot.getChildren())
                {
                    String categoryModel =catS.getValue().toString();
                    catlist.add(categoryModel);
                    HomeAdapter adapter = new HomeAdapter(catlist);
                    catGrid.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //updateNavHeader();
        mReference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = currentUser.getUid();

        View headerView = navigationView.getHeaderView(0);
        final TextView tvUsername = headerView.findViewById(R.id.tv_nav_header_username);
        final TextView tvEmail = headerView.findViewById(R.id.tv_nav_header_email);
        mReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Home.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile: {
                Intent intent = new Intent(Home.this, UserProfile.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_home: {
                Intent intent = new Intent(Home.this, HomePage.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_words:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


    