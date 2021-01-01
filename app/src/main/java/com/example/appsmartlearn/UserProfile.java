package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private FirebaseUser currentUser;
    private DatabaseReference reference;

    private String userID;

    private Button btnLogout;
    private Button btnEdit;
    private Button btnReset;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserProfile.this, HomePage.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        btnLogout = (Button) findViewById(R.id.btn_log_out);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserProfile.this, ActivityLogIn.class);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(UserProfile.this, EditUserProfile.class);
                                           startActivity(intent);
                                       }
                                   }
        );

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = currentUser.getUid();

        final TextView tvUsername2 = (TextView) findViewById(R.id.tv_username2);
        final TextView tvUsername = (TextView) findViewById(R.id.tv_username);
        final TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        final TextView tvDoB = (TextView) findViewById(R.id.tv_date_of_birth);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model_User userProfile = snapshot.getValue(Model_User.class);

                if (userProfile != null) {
                    String username = userProfile.username;
                    String email = userProfile.email;
                    String DoB = userProfile.DoB;

                    tvUsername.setText(username);
                    tvUsername2.setText(username);
                    tvEmail.setText(email);
                    tvDoB.setText(DoB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }
}
