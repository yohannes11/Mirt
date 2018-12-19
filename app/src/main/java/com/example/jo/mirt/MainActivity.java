package com.example.jo.mirt;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainActivity.HttpAsyncTask().execute("http://hacksethiopia.com/mirtapp/get.php");
        mViewPager = findViewById(R.id.container1);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,
                        Main2Activity.class);
                startActivity(myIntent);
            }
        });
    }
    public void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"Home");
        adapter.addFragment(new Tab2Fragment(),"Entertainment");
        adapter.addFragment(new Tab3Fragment(),"People");
        adapter.addFragment(new Tab4Fragment(),"Culture");
        adapter.addFragment(new Tab5Fragment(),"Funny");
        adapter.addFragment(new Tab6Fragment(),"Sport");
        adapter.addFragment(new Tab7Fragment(),"Top 10 Videos");
        viewPager.setAdapter(adapter);


    }
    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }



    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject json = new JSONObject(result);
                int id;
                int category_id;
                String profile_name;
                int profile_id;
                String category_name;
                String category_type;
                JSONArray profiles = json.getJSONArray("profile");
                JSONArray categories = json.getJSONArray("category");
                DBHelper dataHelper=new DBHelper(getBaseContext());
                for (int i = 0; i <categories.length(); i++) {
                    JSONObject jsonData = categories.getJSONObject(i);
                    id=jsonData.getInt("id");
                    category_name=jsonData.getString("name");
                    category_type=jsonData.getString("type");
                    dataHelper.insert_category(id,category_name, category_type);

                }
                for (int i = 0; i <profiles.length(); i++) {
                    JSONObject jsonData = profiles.getJSONObject(i);
                    id=jsonData.getInt("id");
                    profile_name=jsonData.getString("name");
                    profile_id=jsonData.getInt("category_id");
                    dataHelper.insert_profile(id,profile_name, profile_id,"","");

                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

