package com.ne.netbasedserverjsonapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    String jsonstring="", name1,name2;
    HttpHelper_Class httphelper;
    ProgressDialog progressDialog;
    String serveraddress="https://api.androidhive.info/contacts/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1= findViewById(R.id.textdata);
    }

    public void getdataonserver(View view) {
        Asynclass asyn= new Asynclass();
        asyn.execute();
    }

    private class Asynclass extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            httphelper=new HttpHelper_Class();
          jsonstring=httphelper.makeServiceCall(serveraddress);
            try {
                JSONObject jsonObject= new JSONObject (jsonstring);
                JSONArray jsonArray=jsonObject.getJSONArray("contacts");
                JSONObject jsonObject1=jsonArray.getJSONObject(8);
                name1=jsonObject1.getString("name");
                JSONObject jsonObject2=jsonArray.getJSONObject(10);
                name2=jsonObject2.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d("abc",jsonstring+"");
            return null;
        }

        /*@Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            text1.setText(jsonstring);
        }*/

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.cancel();
            text1.setText(name1+ "\n" +name2);

        }
    }
}
