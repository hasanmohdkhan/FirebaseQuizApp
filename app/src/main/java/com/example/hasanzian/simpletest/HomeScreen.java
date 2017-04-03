package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hasanZian on 02-04-2017.
 */

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    public Intent intent;
    public Intent i;
    Button mExam, mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        mExam = (Button) findViewById(R.id.btn_exam);
        mChat = (Button) findViewById(R.id.btn_chat);
        mExam.setOnClickListener(this);
        mChat.setOnClickListener(this);
        intent = new Intent(this, MainActivity.class);
        i = new Intent(this, ChatScreen.class);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exam: {
                startActivity(intent);


            }
            break;
            case R.id.btn_chat: {

                startActivity(i);

            }
            break;


        }


    }
}
