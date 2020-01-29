package com.example.eventscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer timer = new Timer();
        timer.schedule(task,delay);
    }

    private long delay = 5000;
    private TimerTask task = new TimerTask(){
        @Override
        public void run(){
            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
            finish();
        }
    };
}



