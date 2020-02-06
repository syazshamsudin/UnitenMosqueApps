package com.example.eventscheduler;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    public BackgroundWorker(AsyncResponse delegate){
        this.delegate = delegate;
    }
    String r;

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url1 = "http://172.20.10.3:80/login.php";
        if(type.equals("login")){
            try {
                String user_id = params[1];
                String password = params[2];
                URL url = new URL(login_url1);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line ="";
                while((line= bufferedReader.readLine())!=null){
                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String student_profile_url = "http://172.20.10.3:80/student_profile.php";
        if(type.equals("student_profile")){
            try {
                String uid = params[1];
                /*
                String ustatus = params[2];
                String uprogram = params[3];
                String uintake = params[4];
                String ucampus = params[5];
                String uadvisor = params[6];
                */
                URL url = new URL(student_profile_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                /*
                String post_data = URLEncoder.encode("uid", "UTF-8")+"="+URLEncoder.encode(uid, "UTF-8")+"&"
                        +URLEncoder.encode("ustatus", "UTF-8")+"="+URLEncoder.encode(ustatus, "UTF-8")+ "&" +
                        URLEncoder.encode("uprogram", "UTF-8")+"="+URLEncoder.encode(uprogram, "UTF-8")+"&"
                        +URLEncoder.encode("uintake", "UTF-8")+"="+URLEncoder.encode(uintake, "UTF-8") +"&"+
                        URLEncoder.encode("ucampus", "UTF-8")+"="+URLEncoder.encode(ucampus, "UTF-8")+"&"
                        +URLEncoder.encode("uadvisor", "UTF-8")+"="+URLEncoder.encode(uadvisor, "UTF-8");

                 */
                String post_data = URLEncoder.encode("uid", "UTF-8")+"="+URLEncoder.encode(uid, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result ="";
                String line ="";
                while((line= bufferedReader.readLine())!=null){
                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String showallevent_url = "http://172.20.10.3:80/showallevent.php";
        if(type.equals("showallevent")){
            try {
                URL url = new URL(showallevent_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line= bufferedReader.readLine())!=null){
                    result += line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
        /*
        else {
            startActivity(new Intent(LoginActivity.this, StudentProfileActivity.class));
            finish();
        }

         */
        //Toast t = Toast.makeText(context.getApplicationContext(),result,Toast.LENGTH_SHORT);
        //t.setGravity(Gravity.CENTER, 0, 0);
        //t.show();
        /*
        if (result == "Login Success"){
            startActivity(new Intent(context.getApplicationContext(), StudentProfileActivity.class));
        }

         */
        //alertDialog.setMessage(result);
        //alertDialog.show();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
