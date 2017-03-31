package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


//TODO
//TODO 3) next thing to match user selected answer  with correct answer

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ArrayList<String> QuestionArray;
    public ArrayList<String> OptionAArray;
    public ArrayList<String> OptionBArray;
    public ArrayList<String> OptionCArray;
    public ArrayList<String> OptionDArray;//= null;
    public ArrayList<String> mCorrectAnswerArray;//= null;

    public int count = 0;
    public Intent intent;
    public ArrayList<String> mUserInput = new ArrayList<>();
    TextView mTextview;
    Button mButton, mSubmit;
    TextView mContText;
    RadioButton mRadiobuttonA, mRadiobuttonB, mRadiobuttonC, mRadiobuttonD;
    RadioGroup mRadioGroup;
    //firebase code
    DatabaseReference mRef= FirebaseDatabase.getInstance().getReference("Question");
    DatabaseReference mOptionA = FirebaseDatabase.getInstance().getReference("OptionA");
    DatabaseReference mOptionB = FirebaseDatabase.getInstance().getReference("OptionB");
    DatabaseReference mOptionC = FirebaseDatabase.getInstance().getReference("OptionC");
    DatabaseReference mOptionD = FirebaseDatabase.getInstance().getReference("OptionD");
    DatabaseReference RightOption = FirebaseDatabase.getInstance().getReference("RightOption");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mTextview =(TextView) findViewById(R.id.TextView);
        mRadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        mRadiobuttonA = (RadioButton) findViewById(R.id.OptionAdisplay);
        mRadiobuttonB = (RadioButton) findViewById(R.id.OptionBdisplay);
        mRadiobuttonC = (RadioButton) findViewById(R.id.OptionCdisplay);
        mRadiobuttonD = (RadioButton) findViewById(R.id.OptionDdisplay);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                // find which radio button is selected;
                if (checkedId == R.id.OptionAdisplay) {
                    String mRadioButtonValue = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    mUserInput.add(mRadioButtonValue);
                    Toast.makeText(getApplicationContext(), "" + mRadioButtonValue, Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.OptionBdisplay) {

                    String mRadioButtonValue = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    mUserInput.add(mRadioButtonValue);
                    Toast.makeText(getApplicationContext(), "" + mRadioButtonValue, Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.OptionCdisplay) {

                    String mRadioButtonValue = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    mUserInput.add(mRadioButtonValue);
                    Toast.makeText(getApplicationContext(), "" + mRadioButtonValue, Toast.LENGTH_SHORT).show();

                } else if (checkedId == R.id.OptionDdisplay) {

                    String mRadioButtonValue = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    mUserInput.add(mRadioButtonValue);
                    Toast.makeText(getApplicationContext(), "" + mRadioButtonValue, Toast.LENGTH_SHORT).show();

                }


            }
        });





        mContText = (TextView) findViewById(R.id.textCount);
        mButton = (Button) findViewById(R.id.nextButton);
        mSubmit = (Button) findViewById(R.id.submit);
        mButton.setOnClickListener(this);


        intent = new Intent(this, ResultActivity.class);
        intent.putStringArrayListExtra("Key", mUserInput);


        // to checking question







        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });







    }

    @Override
    protected  void onStart(){
     super.onStart();

        mRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    QuestionArray = (ArrayList) dataSnapshot.getValue();
                    //  Log.e("!)@@>>", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    //Log.e("!_@@", QuestionArray.get(0) + ""); // use your counter value instead of 0
                    String text = child.getValue(String.class);
                    mContText.setText(text);
                    //checking for data load




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
                    OptionBArray = (ArrayList) dataSnapshot.getValue();
                    //  Log.e("OptionB", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
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
                    OptionDArray = (ArrayList) dataSnapshot.getValue();
                    String text = child.getValue(String.class);
                    mContText.setText(text);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Correct Answer Code For storing correct Answer
        RightOption.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mCorrectAnswerArray = (ArrayList) dataSnapshot.getValue();
                    //Log.e("Corrtect Answer", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    Log.e("!)@@>>", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    String text = child.getValue(String.class);
                    mContText.setText(text);
                }


                intent.putStringArrayListExtra("Ans", mCorrectAnswerArray);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Boolean mArrayStatus







// end of OnStart method
    }



    @Override
    public void onClick(View v) {


        mTextview.setText(QuestionArray.get(count).toString());
        mRadiobuttonA.setText(OptionAArray.get(count).toString());
        mRadiobuttonB.setText(OptionBArray.get(count).toString());
        mRadiobuttonC.setText(OptionCArray.get(count).toString());
        mRadiobuttonD.setText(OptionDArray.get(count).toString());

        if (count > 0) {
            mRadioGroup.clearCheck();

        }

        count++;


        // TODO: 24-03-2017  We have to move submit code to separate method
        //  TODO: so that submit button dont incresment the counter <--- Problem;


        if (v == mSubmit) {
            //String for geting selected Radio Button
            //String mRadioButtonValue = ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString();
            //Adding user selected Input to array for later checking for answer
            // mUserInput.add(mRadioButtonValue);
            // Toast.makeText(this,"S: "+mRadioButtonValue,Toast.LENGTH_SHORT).show();
            //mUserInput.toString();


        }


    }




    // end of MainActivity
}
