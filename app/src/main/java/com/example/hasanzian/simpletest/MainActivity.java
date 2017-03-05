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

    public ArrayList<String> QuestionArray = null;
    public ArrayList<String> OptionAArray = null;
    public ArrayList<String> OptionBArray = null;
    public ArrayList<String> OptionCArray = null;
    public ArrayList<String> OptionDArray = null;
    public int count = 0;
    TextView mTextview;
    Button mButton;
    TextView mContText;
    RadioButton mRadiobuttonA, mRadiobuttonB, mRadiobuttonC, mRadiobuttonD;
    DatabaseReference mRef= FirebaseDatabase.getInstance().getReference("Question");
    DatabaseReference mOptionA = FirebaseDatabase.getInstance().getReference("OptionA");
    DatabaseReference mOptionB = FirebaseDatabase.getInstance().getReference("OptionB");
    DatabaseReference mOptionC = FirebaseDatabase.getInstance().getReference("OptionC");
    DatabaseReference mOptionD = FirebaseDatabase.getInstance().getReference("OptionD");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mTextview =(TextView) findViewById(R.id.TextView);
        mRadiobuttonA = (RadioButton) findViewById(R.id.OptionAdisplay);
        mRadiobuttonB = (RadioButton) findViewById(R.id.OptionBdisplay);
        mRadiobuttonC = (RadioButton) findViewById(R.id.OptionCdisplay);
        mRadiobuttonD = (RadioButton) findViewById(R.id.OptionDdisplay);
        mContText = (TextView) findViewById(R.id.textCount);
        mButton = (Button) findViewById(R.id.nextButton);
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


                    QuestionArray = (ArrayList) dataSnapshot.getValue();
                    Log.e("!)@@>>", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    Log.e("!_@@", QuestionArray.get(0) + ""); // use your counter value instead of 0
                    String text = child.getValue(String.class);
                    mContText.setText(text);


                }







            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mOptionA.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot child : dataSnapshot.getChildren()) {
                   // TODO: handle the post


                   OptionAArray = (ArrayList) dataSnapshot.getValue();

                   String text = child.getValue(String.class);
                   mContText.setText(text);


               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
        });
        //Option B code
        mOptionB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // TODO: handle the post


                    OptionBArray = (ArrayList) dataSnapshot.getValue();
                    Log.e("OptionB", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());

                    String text = child.getValue(String.class);
                    mContText.setText(text);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Option C code
        mOptionC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // TODO: handle the post


                    OptionCArray = (ArrayList) dataSnapshot.getValue();

                    String text = child.getValue(String.class);
                    mContText.setText(text);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Option D code
        mOptionD.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // TODO: handle the post


                    OptionDArray = (ArrayList) dataSnapshot.getValue();

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
        mTextview.setText(QuestionArray.get(count).toString());
        mRadiobuttonA.setText(OptionAArray.get(count).toString());
        mRadiobuttonB.setText(OptionBArray.get(count).toString());
        mRadiobuttonC.setText(OptionCArray.get(count).toString());
        mRadiobuttonD.setText(OptionDArray.get(count).toString());


        count++;


    }


}
