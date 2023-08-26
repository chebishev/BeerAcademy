package com.example.android.beeracademy;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //this method will be executed when "tap here to continue" is tapped
    public void forwardToEducationalPart(View view){

        //forward to the education window
                Intent educationPart = new Intent(this, EducationPart.class);
        startActivity(educationPart);
    }
}
