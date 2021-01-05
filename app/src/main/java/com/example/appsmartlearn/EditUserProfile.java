package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditUserProfile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    String username, DoB, password;
    private EditText etNewUsername, etNewDoB, etPassword;
    private Button btnCancel, btnSave, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        btn_back = (Button) findViewById(R.id.btn_back_edit);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        etNewUsername = (EditText) findViewById(R.id.et_new_username);
        etNewDoB = (EditText) findViewById(R.id.et_new_date_of_birth);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSave = (Button) findViewById(R.id.btn_save);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model_User userProfile = snapshot.getValue(Model_User.class);

                if (userProfile != null) {
                    username = userProfile.username;
                    DoB = userProfile.DoB;
                    password = userProfile.password;

                    etNewUsername.setText(username);
                    etNewDoB.setText(DoB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserProfile.this, UserProfile.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateUserProfile(v);
            }
            private void updateUserProfile(View v) {
                final String newUsername = etNewUsername.getText().toString().trim();
                final String newDoB = etNewDoB.getText().toString().trim();
                final String Password = etPassword.getText().toString().trim();

                if(newUsername.isEmpty()) {
                    etNewUsername.setError("Please type your new username!");
                    etNewUsername.requestFocus();
                    return;
                }
                if(newDoB.isEmpty()) {
                    etNewDoB.setError("Please type your date of birth!");
                    etNewDoB.requestFocus();
                    return;
                }
                if(Password.isEmpty()) {
                    etPassword.setError("Type your password to continue!");
                    etPassword.requestFocus();
                    return;
                }
                if(!Password.equals(password)) {
                    etPassword.setError("Your password is incorrect!");
                    etPassword.requestFocus();
                    return;
                }
                reference.child(userID).child("username").setValue(etNewUsername.getText().toString());
                username = etNewUsername.getText().toString();
                reference.child(userID).child("DoB").setValue(etNewDoB.getText().toString());
                DoB = etNewDoB.getText().toString();

                Toast.makeText(EditUserProfile.this, "Profile has been updated!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditUserProfile.this, UserProfile.class);
                startActivity(intent);
                /*if(isNameChanged() && isDateOfBirthChanged() || isDateOfBirthChanged() || isNameChanged()) {
                    Toast.makeText(EditUserProfile.this, "Profile has been updated!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditUserProfile.this, UserProfile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditUserProfile.this, "Data is same and can not be updated!", Toast.LENGTH_LONG).show();
                }*/
            }
            private boolean isNameChanged() {
                if(!username.equals(etNewUsername.getText().toString())) {
                    reference.child(userID).child("username").setValue(etNewUsername.getText().toString());
                    username = etNewUsername.getText().toString();
                    return true;
                } else {
                    return true;
                }
            }

            private boolean isDateOfBirthChanged() {
                if(!DoB.equals(etNewDoB.getText().toString())) {
                    reference.child(userID).child("DoB").setValue(etNewDoB.getText().toString());
                    DoB = etNewDoB.getText().toString();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}



       /*
    }

    public void updateUserProfile(View view) {
        if(isNameChanged() || isPasswordChange()) {
            Toast.makeText(this, "Profile has been updated!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data is same and can not be updated!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNameChanged() {
        if(!username.equals(etNewUsername.getText().toString())) {
            reference.child(username).child("username").setValue(etNewUsername.getText().toString());
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChange() {
        if(!password.equals(etNewPassword.getText().toString())) {
            reference.child(password).child("password").setValue(etNewPassword.getText().toString());
            return true;
        } else {
            return false;
        }
    }
}
*/