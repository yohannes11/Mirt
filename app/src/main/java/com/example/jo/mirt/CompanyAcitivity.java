package com.example.jo.mirt;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class CompanyAcitivity extends Activity {

    ListView listView ;
    TextView toptext;
    String[] values;
    String[] categories;
    String[] indexes;
    String[] test;
    String toptextcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_acitivity);

        String s=getIntent().getStringExtra("company_id");
        DBHelper helping = new DBHelper(this);
        String toptextcontent=helping.getCompanyCategory(s);
        int category_count=helping.getCompaniesCount(s);
        categories=new String[category_count];
        indexes =new String[category_count];
        Cursor stockList=helping.getAllComapnies(s);
        int count=0;
        while(stockList.isAfterLast() == false){

            categories[count]=stockList.getString(stockList.getColumnIndex("name" ));
            indexes[count]=stockList.getString(stockList.getColumnIndex("id" ));
            stockList.moveToNext();
            count++;
        }


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        toptext = (TextView) findViewById(R.id.toptext);

        // Defined Array values to show in ListView

             toptext.setText(toptextcontent);



        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categories);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(CompanyAcitivity.this, ScrollingActivity.class);
                //i.putExtra("comapny_id",position);
                i.putExtra("company_id",indexes[position]+"");
                startActivity(i);
                ((Activity) CompanyAcitivity.this).overridePendingTransition(0, 0);

            }

        });


    }
}
