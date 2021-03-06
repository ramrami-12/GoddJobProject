package com.goodjob.singing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChoiceLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_level);

        Button start_low = (Button)findViewById(R.id.levellow);
        Button start_middle = (Button)findViewById(R.id.levelmiddle);
        Button start_high = (Button)findViewById(R.id.levelhigh);
        Button back = (Button)findViewById(R.id.backtochoice);
        Button home = (Button)findViewById(R.id.home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (
                        getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (
                        getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });

        start_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent (
                        getApplicationContext(), LowChoiceSong.class);
                startActivity(intent1);
            }
        });

        start_middle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2 = new Intent (
                        getApplicationContext(), MiddleChoiceSong.class);
                startActivity(intent2);
            }
        });

        start_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent (
                        getApplicationContext(), HighChoiceSong.class);
                startActivity(intent3);
            }
        });
    }
}