package com.goodjob.singing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class HighChoiceSong extends AppCompatActivity {
    String sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_choice_song);
        Button start_ods = (Button)findViewById(R.id.song_ods);
        CheckBox man = (CheckBox) findViewById(R.id.man);
        CheckBox woman = (CheckBox) findViewById(R.id.woman);

        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex="man";
            }
        });

        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex="woman";
            }
        });

        start_ods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (
                        getApplicationContext(), PartPracticeODS1.class);
                intent.putExtra("sex", sex);
                startActivity(intent);
            }
        });
    }


}