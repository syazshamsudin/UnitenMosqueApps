package com.example.eventscheduler;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {


    EditText studID, studPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        studID = (EditText)findViewById(R.id.studID);
        studPassword = (EditText)findViewById(R.id.studPassword);



        }

    public void OnLogin(View view){
        String user_name = studID.getText().toString();
        String password = studPassword.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, user_name, password);




    }

    }

