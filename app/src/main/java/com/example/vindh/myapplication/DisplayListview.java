package com.example.vindh.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayListview extends AppCompatActivity {

    JSONObject jsobj;
    JSONArray jsarray;
    String js2,js3;
    contactadapter ca;
    ListView ls;
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_listview);

        js2=getIntent().getExtras().getString("json_data");
        ca=new contactadapter(this,R.layout.row_layout);
        ls=(ListView) findViewById(R.id.listv);
        ls.setAdapter(ca);

        try{


            jsobj=new JSONObject(js2);
            jsarray =jsobj.getJSONArray("result");
            int count=0;
            String press,time,temp,humid;


                JSONObject obj = jsarray.getJSONObject(count);
                press=obj.getString("pressure");
                time=obj.getString("time");
                temp=obj.getString("temp");
                humid=obj.getString("humidity");

                contacts c = new contacts(press,time,temp,humid);
                ca.add(c);





        }
        catch (JSONException ex){
            ex.printStackTrace();
        }
    }

    public void getjs(View view) {
        new DisplayListview.BackgroundTask().execute();


    }

  private class BackgroundTask extends AsyncTask<Void, Void, String> {
        String JSON_URL;




        @Override
        protected void onPreExecute() {
            JSON_URL =""; //Enter URL
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
            Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
            js3 = result;
            parsejs(js3);



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
