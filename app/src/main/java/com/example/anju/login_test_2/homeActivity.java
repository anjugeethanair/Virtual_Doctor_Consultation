package com.example.anju.login_test_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homeActivity extends AppCompatActivity {
    //private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    // globally
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //in your OnCreate() method
        if (currentUser != null) {
            TextView usernameobj = (TextView) findViewById(R.id.username);
            usernameobj.setText(currentUser.getDisplayName());
        }
    }


}
