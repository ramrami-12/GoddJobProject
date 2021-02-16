package com.goodjob.singing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class HighChoiceSong extends AppCompatActivity {
    String sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_choice_song);
        Button start_ods = (Button)findViewById(R.id.song_ods);
        RadioButton man = (RadioButton) findViewById(R.id.man);
        RadioButton woman = (RadioButton) findViewById(R.id.woman);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.man){
                    sex="man";
                }else {
                    sex="woman";
                }
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
