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

    Button Btn_exam, Btn_Gate,Btn_App;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_activity);
        Btn_exam=(Button)findViewById(R.id.btn_category_one_exam);
        Btn_Gate=(Button)findViewById(R.id.btn_category_two);
        Btn_App=(Button)findViewById(R.id.btn_category_three);

        Btn_exam.setOnClickListener(this);
        Btn_Gate.setOnClickListener(this);
        Btn_App.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id) {
            case R.id.btn_category_one_exam:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_category_two:
                Intent intent1 = new Intent(this, GateAcivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_category_three:
                Intent intent2 = new Intent(this, ThirdExamClass.class);
                startActivity(intent2);
                break;




        }


    }
}
