package com.example.eventscheduler;

import android.icu.text.Transliterator;
import android.os.Bundle;
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

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ShowAllEventsActivity extends AppCompatActivity {
    @BindView(R.id.EventListView) ListView listView;
    //ListView listView;
    public ArrayList<MasjidEvent> exampleList;
    public ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_events_page);
        ButterKnife.bind(this);

        listView = (ListView) findViewById(R.id.EventListView);
        exampleList = new ArrayList<>();

      /*  //get from DB
        String nm;
        try {
            Connection con = DBConnection.CONN();
            if (con == null) {
                //no internet connection
            } else {

                String query = " select * from masjid_event";

                Statement stmt = con.createStatement();
                // stmt.executeUpdate(query);

                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    nm = rs.getString(1);
                    exampleList.add(new MasjidEvent(nm,"s1","s1"));
                }
            }
        }
        catch (Exception ex) {
            //error
        }*/

        adapter = new ArrayAdapter<MasjidEvent>(this,R.layout.adapter_view_layout, exampleList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                String event_name = getItem(position).getEvent_name();
                String date = getItem(position).getDate();
                String duration = getItem(position).getDuration();

                MasjidEvent event = new MasjidEvent(event_name,date,duration);

                LayoutInflater inflater = LayoutInflater.from(this.getContext());
                convertView = inflater.inflate(R.layout.adapter_view_layout,parent,false);

                TextView event_name_view = (TextView) convertView.findViewById(R.id.textView3);
                TextView date_view = (TextView) convertView.findViewById(R.id.textView1);
                TextView duration_view = (TextView) convertView.findViewById(R.id.textView2);


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
                if(i==1)
                    Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
            }
        });

    }
}