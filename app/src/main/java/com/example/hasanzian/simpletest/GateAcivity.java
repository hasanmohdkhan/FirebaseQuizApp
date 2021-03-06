package com.example.hasanzian.simpletest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

/**
 * Created by hasanZian on 20-05-2017.
 */

public class GateAcivity extends AppCompatActivity implements View.OnClickListener {

    public ArrayList<String> QuestionArray;
    public ArrayList<String> OptionAArray;
    public ArrayList<String> OptionBArray;
    public ArrayList<String> OptionCArray;
    public ArrayList<String> OptionDArray;
    public ArrayList<String> mCorrectAnswerArray;
    public int count = 0;
    public Intent intent;
    public ArrayList<String> mUserInput = new ArrayList<>();
    TextView mTextview;
    Button mButton, mSubmit;
    TextView mContText;
    RadioButton mRadiobuttonA, mRadiobuttonB, mRadiobuttonC, mRadiobuttonD;
    RadioGroup mRadioGroup;
    //firebase code
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("GQue");
    DatabaseReference mOptionA = FirebaseDatabase.getInstance().getReference("GOpA");
    DatabaseReference mOptionB = FirebaseDatabase.getInstance().getReference("GOpB");
    DatabaseReference mOptionC = FirebaseDatabase.getInstance().getReference("GOpC");
    DatabaseReference mOptionD = FirebaseDatabase.getInstance().getReference("GOpD");
    DatabaseReference RightOption = FirebaseDatabase.getInstance().getReference("GAns");
    TextView textTimer;
    MyCountDownTimer myCountDownTimer;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRadioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        mRadiobuttonA = (RadioButton) findViewById(R.id.OptionAdisplay);
        mRadiobuttonB = (RadioButton) findViewById(R.id.OptionBdisplay);
        mRadiobuttonC = (RadioButton) findViewById(R.id.OptionCdisplay);
        mRadiobuttonD = (RadioButton) findViewById(R.id.OptionDdisplay);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        textTimer=(TextView)findViewById(R.id.TextTimerShow);
        mRadioGroup.setVisibility(View.INVISIBLE);




        mProgressBar.setVisibility(ProgressBar.VISIBLE);
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
        mContText.setVisibility(View.INVISIBLE);
        mButton.setVisibility(View.INVISIBLE);
        mSubmit.setVisibility(View.INVISIBLE);

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

                mProgressBar.setVisibility(ProgressBar.INVISIBLE);


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    QuestionArray = (ArrayList) dataSnapshot.getValue();
                    //  Log.e("!)@@>>", dataSnapshot.getKey() + " " + dataSnapshot.getValue() + " " + dataSnapshot.getChildren() + " " + dataSnapshot.getChildrenCount());
                    //Log.e("!_@@", QuestionArray.get(0) + ""); // use your counter value instead of 0
                    String text = child.getValue(String.class);
                    mContText.setText(text);
                    //checking for data load




                }
                myCountDownTimer = new MyCountDownTimer(100000, 1000);
                myCountDownTimer.start();
                mButton.setVisibility(View.VISIBLE);
                mSubmit.setVisibility(View.VISIBLE);

                mTextview = (TextView) findViewById(R.id.TextView);
                mTextview.setText(QuestionArray.get(count).toString());

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
                mRadioGroup.setVisibility(View.VISIBLE); //setting Radio group to visible

                mRadiobuttonA.setText(OptionAArray.get(count).toString());

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
                mRadiobuttonB.setText(OptionBArray.get(count).toString());
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

                mRadiobuttonC.setText(OptionCArray.get(count).toString());
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
                mRadiobuttonD.setText(OptionDArray.get(count).toString());
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
        int mArraySize = QuestionArray.size();
        Log.d("mArray Size :", " " + mArraySize);
        count++;

        if (mArraySize == count) {
            count = 0;
            Log.d("hasan:", " " + count);
        }





        mTextview.setText(QuestionArray.get(count).toString());
        mRadiobuttonA.setText(OptionAArray.get(count).toString());
        mRadiobuttonB.setText(OptionBArray.get(count).toString());
        mRadiobuttonC.setText(OptionCArray.get(count).toString());
        mRadiobuttonD.setText(OptionDArray.get(count).toString());

        if (count > 0) {
            mRadioGroup.clearCheck();

        }


    }

    class MyCountDownTimer extends CountDownTimer {


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/1000);
            textTimer.setText("Time Left: "+progress);


        }

        @Override
        public void onFinish() {
            myCountDownTimer.cancel();
            textTimer.setVisibility(View.INVISIBLE);
            startActivity(intent);


        }
    }





    // end of MainActivity
}

