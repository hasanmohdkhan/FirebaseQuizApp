package com.example.hasanzian.simpletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextview;
    Button mButton;
    TextView mContText;
    RadioButton mRadiobutton;

    public   ArrayList<String> a = null;
    public   ArrayList<String> b = null;
    public   int  count=0;
    DatabaseReference mRef= FirebaseDatabase.getInstance().getReference("Question");
    DatabaseReference mOption= FirebaseDatabase.getInstance().getReference("Option");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mTextview =(TextView) findViewById(R.id.TextView);
    mRadiobutton=(RadioButton)findViewById(R.id.radioButton);
    mContText=(TextView)findViewById(R.id.textCount);
    mButton =(Button)findViewById(R.id.nextButton);
    mButton.setOnClickListener(this);

    }

    @Override
    protected  void onStart(){
     super.onStart();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // TODO: handle the post


                    a = (ArrayList) dataSnapshot.getValue();
                    Log.e("!)@@>>", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    Log.e("!_@@", a.get(0) + ""); // use your counter value instead of 0
                    String text = child.getValue(String.class);
                    mContText.setText(text);


                }







            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       mOption.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot child : dataSnapshot.getChildren()) {
                   // TODO: handle the post


                   b = (ArrayList) dataSnapshot.getValue();

                   String text = child.getValue(String.class);
                   mContText.setText(text);


               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


    }



    @Override
    public void onClick(View v) {
        mTextview.setText(a.get(count).toString());
        mRadiobutton.setText(b.get(count).toString());
        count++;


    }


}
