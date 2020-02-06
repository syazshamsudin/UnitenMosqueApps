package com.example.eventscheduler;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventscheduler.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ShowAllEventsActivity extends AppCompatActivity {
    @BindView(R.id.EventListView) ListView listView;
    //ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_events_page);
        ButterKnife.bind(this);

        listView = (ListView) findViewById(R.id.EventListView);

        final BackgroundWorker backgroundworker = new BackgroundWorker(new BackgroundWorker.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Toast t = Toast.makeText(ShowAllEventsActivity.this,output,Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER, 0, 0);
                t.show();

                String[] strsplit1 = output.split(";");



                ArrayList<MasjidEvent> event_list = new ArrayList<>();
                ArrayAdapter adapter;

                for (int i = 0; i < strsplit1.length; i++){
                    String[] strsplit2 = strsplit1[i].split(",");
                    MasjidEvent event = new MasjidEvent(strsplit2[1],strsplit2[2],strsplit2[3]);
                    event_list.add(event);
                    //Toast.makeText(ShowAllEventsActivity.this,strall[i] ,Toast.LENGTH_LONG).show();
                }

                adapter = new ArrayAdapter<MasjidEvent>(getApplicationContext(),R.layout.adapter_view_layout, event_list){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        String event_name = getItem(position).getEvent_name();
                        String date = getItem(position).getDate();
                        String duration = getItem(position).getDuration();



                        LayoutInflater inflater = LayoutInflater.from(this.getContext());
                        convertView = inflater.inflate(R.layout.adapter_view_layout,parent,false);

                        TextView event_name_view = (TextView) convertView.findViewById(R.id.eventname);
                        TextView date_view = (TextView) convertView.findViewById(R.id.date);
                        TextView duration_view = (TextView) convertView.findViewById(R.id.duration);


                        event_name_view.setText(event_name);
                        date_view.setText(date);
                        duration_view.setText(duration);



                        return convertView;
                    }
                };

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Toast.makeText(getApplicationContext(),"This event was added to your schedule..",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ShowAllEventsActivity.this, MainMenuActivity.class));
                            finish();
                    }
                });

            }
        });
        backgroundworker.execute("showallevent");

    }



}