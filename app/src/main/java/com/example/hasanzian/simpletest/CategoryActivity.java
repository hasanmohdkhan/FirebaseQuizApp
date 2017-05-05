package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hasanZian on 05-05-2017.
 */

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    Button Btn_exam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_activity);
        Btn_exam=(Button)findViewById(R.id.btn_category_one_exam);

        Btn_exam.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
