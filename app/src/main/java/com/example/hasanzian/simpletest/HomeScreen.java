package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import static com.example.hasanzian.simpletest.ChatScreen.ANONYMOUS;

/**
 * Created by hasanZian on 02-04-2017.
 */

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    public static final int RC_SIGN_IN = 1;
    public Intent intent;
    public Intent i;
    Button mExam, mChat;
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_cardvvew);
        mUsername = ANONYMOUS;
        mFirebaseAuth = FirebaseAuth.getInstance();

        mExam = (Button) findViewById(R.id.btnExam);
        mChat = (Button) findViewById(R.id.btnChat);
        mExam.setOnClickListener(this);
        mChat.setOnClickListener(this);
        intent = new Intent(this, CategoryActivity.class);
        i = new Intent(this, ChatScreen.class);

        //new Auth listener
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //checking for user or getting current user;
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String name = user.getDisplayName();
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(getApplicationContext(), "Welcome..." + name, Toast.LENGTH_SHORT).show();
                    onSignInInitializer(user.getDisplayName());

                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                    onSignOutCleanup();


                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder().setIsSmartLockEnabled(false)
                                    .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);


                }

            }


        };











    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnExam: {

                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Exam", Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.btnChat: {

                startActivity(i);
                Toast.makeText(getApplicationContext(), "chat", Toast.LENGTH_SHORT).show();
            }
            break;


        }


    }

// Sign Method

    private void onSignInInitializer(String username) {
        mUsername = username;

    }

    private void onSignOutCleanup() {
    }













}
