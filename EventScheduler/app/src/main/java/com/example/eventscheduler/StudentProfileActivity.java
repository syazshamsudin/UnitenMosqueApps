package com.example.eventscheduler;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProfileActivity extends AppCompatActivity {

    EditText text1, text2, text3, text4, text5, text6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile_page);

        text1 = (EditText) findViewById(R.id.content1);
        text2 = (EditText) findViewById(R.id.content2);
        text3 = (EditText) findViewById(R.id.content3);
        text4 = (EditText) findViewById(R.id.content4);
        text5 = (EditText) findViewById(R.id.content5);
        text6 = (EditText) findViewById(R.id.content6);


    }


}
