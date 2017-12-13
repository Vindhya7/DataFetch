package com.example.vindh.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    String JSON_STRING;
    String js1;
    JSONObject jsobj;
    JSONArray jsarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void getjson(View view) {
        new BackgroundTask().execute();
    }





    private class BackgroundTask extends AsyncTask<Void, Void, String> {
         String JSON_URL;




        @Override
        protected void onPreExecute() {
            JSON_URL ="http://10.56.10.173/bme/t.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                StringBuilder JSON_DATA = new StringBuilder();
                URL url = new URL(JSON_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((JSON_STRING = reader.readLine())!=null) {
                    JSON_DATA.append(JSON_STRING).append("\n");
                }
                return JSON_DATA.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),"Connection perfect",Toast.LENGTH_LONG).show();
            js1 = result;
            parsejs(js1);



        }


    }

    private void parsejs(String js) {
        if(js==null){
            Toast.makeText(getApplicationContext(),"Check connection",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent=new Intent(this,DisplayListview.class);
            intent.putExtra("json_data",js);
            startActivity(intent);
        }
    }


}

