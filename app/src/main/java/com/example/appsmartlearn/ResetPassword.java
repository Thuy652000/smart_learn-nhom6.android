package com.example.appsmartlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResetPassword extends AppCompatActivity {

    private Button btnSave, btnCancel;
    private TextView tvUsername;
    private EditText etCurrentPassword, etNewPassword, etConfirmPassword;
    String username, password;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        etCurrentPassword = (EditText) findViewById(R.id.et_current_password);
        etNewPassword = (EditText) findViewById(R.id.et_new_password);
        etConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);
        tvUsername = (TextView) findViewById(R.id.tv_username2);

        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Model_User userProfile = snapshot.getValue(Model_User.class);

                if (userProfile != null) {
                    username = userProfile.username;
                    password = userProfile.password;

                    tvUsername.setText(username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, UserProfile.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(v);
            }
            private void resetPassword(View v) {

                final String currentPassword = etCurrentPassword.getText().toString().trim();
                final String newPassword = etNewPassword.getText().toString().trim();
                final String confirmPassword = etConfirmPassword.getText().toString().trim();

                if(currentPassword.isEmpty()) {
                    etCurrentPassword.setError("Please type your current password!");
                    etCurrentPassword.requestFocus();
                    return;
                }
                if(!currentPassword.equals(password)) {
                    etCurrentPassword.setError("Your current password is incorrect!");
                    etCurrentPassword.requestFocus();
                    return;
                }
                if(newPassword.isEmpty()) {
                    etNewPassword.setError("Please type your new password!");
                    etNewPassword.requestFocus();
                    return;
                }

                if(newPassword.length() < 6) {
                    etNewPassword.setError("New password must be longer than 6 characters!");
                    etNewPassword.requestFocus();
                    return;
                }

                if(confirmPassword.isEmpty()) {
                    etConfirmPassword.setError("Please confirm your password!");
                    etConfirmPassword.requestFocus();
                    return;
                }
                if(!confirmPassword.equals(newPassword)) {
                    etConfirmPassword.setError("The password confirmation does not match!");
                    etConfirmPassword.requestFocus();
                    return;
                }

                if (isPasswordChanged()) {
                    Intent intent = new Intent(ResetPassword.this, UserProfile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ResetPassword.this, "Password is same and can not be updated!", Toast.LENGTH_LONG).show();
                }
        }
            private boolean isPasswordChanged() {
                if(!password.equals(etNewPassword.getText().toString())) {
                    reference.child(userID).child("password").setValue(etNewPassword.getText().toString());
                    password = etNewPassword.getText().toString();

                    user.updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ResetPassword.this, "Password changed!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ResetPassword.this, "Password change failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
