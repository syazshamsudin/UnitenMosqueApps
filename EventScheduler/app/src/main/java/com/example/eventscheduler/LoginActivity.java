package com.example.eventscheduler;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

import static androidx.core.content.ContextCompat.startActivity;


public class LoginActivity extends AppCompatActivity {
    EditText studID, studPassword;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        studID = (EditText) findViewById(R.id.studID);
        studPassword = (EditText) findViewById(R.id.studPassword);
        button = findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = studID.getText().toString();
                String password = studPassword.getText().toString();
                String type = "login";

                if (user_name.isEmpty() || password.isEmpty()){
                    Toast t = Toast.makeText(LoginActivity.this,"Please fill in username and password.." ,Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }
                else{
                    //run
                    BackgroundWorker backgroundworker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            //Toast t = Toast.makeText(LoginActivity.this,output ,Toast.LENGTH_SHORT);
                            //t.setGravity(Gravity.CENTER, 0, 0);
                            //t.show();
                            String[] strall = output.split(",");
                            if (strall[0].equals("success")){
                                if (strall[1].equals("admin")){
                                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                                    finish();
                                }
                                else {
                                    Intent myIntent = new Intent(LoginActivity.this, StudentProfileActivity.class);
                                    myIntent.putExtra("student_ID",strall[1]);
                                    startActivity(myIntent);
                                    finish();
                                }
                            }
                        }
                    });
                    backgroundworker.execute(type, user_name, password);
                }

/*
                while (!backgroundworker.getStatus().equals(AsyncTask.Status.FINISHED))
                {
                    //keep looping
                }

 */
/*
                String r = backgroundworker.getFinalResult();
                Toast t = Toast.makeText(LoginActivity.this,r ,Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();
*/
                /*
                if (user_name.isEmpty() || password.isEmpty()){
                    Toast t = Toast.makeText(LoginActivity.this,"Please fill in username and password.." ,Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER, 0, 0);
                    t.show();
                }
                else if (user_name.equals("admin") && password.equals("admin")){
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(LoginActivity.this, StudentProfileActivity.class));
                    finish();
                }

                 */
            }
        });
    }
}




