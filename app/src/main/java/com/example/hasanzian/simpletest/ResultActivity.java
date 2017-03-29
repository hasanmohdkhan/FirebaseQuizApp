package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hasanZian on 25-03-2017.
 */

public class ResultActivity extends AppCompatActivity {

    TextView mResultView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        Intent intent = getIntent();
        ArrayList<String> result = intent.getStringArrayListExtra("Key");
        ArrayList<String> mAnsKeys = intent.getStringArrayListExtra("Ans");

        textView2 = (TextView) findViewById(R.id.textView2);
        mResultView = (TextView) findViewById(R.id.ResultView);

        mResultView.setText(result.toString());
        // mResultView.setText("------AnsKey------");
        textView2.setText(mAnsKeys.toString());



    }
}
