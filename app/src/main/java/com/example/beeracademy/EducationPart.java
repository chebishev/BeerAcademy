package com.example.beeracademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EducationPart extends AppCompatActivity {
    TextView informationBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_part);

        //changeable textView on button click
        informationBox = (TextView) findViewById(R.id.cheers);
    }

    //on first button click displays specified string
    public void firstThing(View view) {
        informationBox.setText(getString(R.string.first_string));
    }

    //on second button click displays specified string
    public void secondThing(View view) {
        informationBox.setText(getString(R.string.second_string));
    }

    //on third button click displays specified string
    public void thirdThing(View view) {
        informationBox.setText(getString(R.string.third_string));
    }

    //on fourth button click displays specified string
    public void fourthThing(View view) {
        informationBox.setText(getString(R.string.fourth_string));
    }

    //on fifth button click displays specified string
    public void fifthThing(View view) {
        informationBox.setText(getString(R.string.fifth_string));
    }

    //on click listener for the "More Beer?" button, forwards to the JustBeer application
    public void forwardToMoreBeerPart(View view) {

        // it leads to the last page of the program with all the calculations, e-mail references and... you'll see :)
        Intent justBeer = new Intent(EducationPart.this, MoreBeer.class);
        startActivity(justBeer);
    }
}