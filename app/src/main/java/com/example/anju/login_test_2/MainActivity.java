package com.example.anju.login_test_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    public
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.google_signIn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void google_SignIn() {
      /*  Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);*/

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LOG Auth", "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                                startActivity(intent);

                            } else {

                            }

                            // ...
                        }
                    });
        } else {
            Intent intent = new Intent(getApplicationContext(), homeActivity.class);
            startActivity(intent);
        }

    }

    public void fb_SignIn(View view) {
        Intent intent = new Intent(this, homeActivity.class);
        startActivity(intent);
    }

    public void callRegister(View view) {
        EditText emailobj = (EditText) findViewById(R.id.email);
        String value_email = emailobj.getText().toString();
        EditText passwordobj = (EditText) findViewById(R.id.password);
        String value_pass = passwordobj.getText().toString();

        mAuth.signInWithEmailAndPassword(value_email, value_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, homeActivity.class);
                    startActivity(intent);
                    MainActivity.value_email = "dsf";
                } else {

                }
            }
        });


        mAuth.createUserWithEmailAndPassword(value_email, value_pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("auth_log", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, homeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("auth_log", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });


        // Do something in response to button click
/*
        if(Singleton.getMap().containsKey(value_email)){
            Intent intent = new Intent(this, homeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, signupActivity.class);
            startActivity(intent);
        }*/
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_signIn:
                google_SignIn();
                break;
        }

    }


}
