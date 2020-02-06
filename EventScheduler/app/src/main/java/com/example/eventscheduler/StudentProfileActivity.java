package com.example.eventscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProfileActivity extends AppCompatActivity {

    TextView studentID, studentName, studentStatus, studentProgram, studentIntake, studentCampus, studentAdvisor;

    String student_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile_page);

        studentName = (TextView)findViewById(R.id.student_name);
        studentID = (TextView) findViewById(R.id.content1);
        studentStatus = (TextView) findViewById(R.id.content2);
        studentProgram = (TextView) findViewById(R.id.content3);
        studentIntake = (TextView) findViewById(R.id.content4);
        studentCampus = (TextView) findViewById(R.id.content5);
        studentAdvisor = (TextView) findViewById(R.id.content6);

        Intent myIntent = getIntent(); // gets the previously created intent
        String stID = myIntent.getStringExtra("student_ID");
        studentID.setText(stID);

        BackgroundWorker backgroundworker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                //Toast t = Toast.makeText(StudentProfileActivity.this,output ,Toast.LENGTH_LONG);
                //t.setGravity(Gravity.CENTER, 0, 0);
                //t.show();

                String[] strall = output.split(",");
                studentName.setText(strall[0]);
                studentStatus.setText(strall[1]);
                studentProgram.setText(strall[2]);
                studentIntake.setText(strall[3]);
                studentCampus.setText(strall[4]);
                studentAdvisor.setText(strall[5]);


            }
        });
        backgroundworker.execute("student_profile", stID);




    }






}
