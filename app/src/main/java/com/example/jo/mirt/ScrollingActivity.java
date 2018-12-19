package com.example.jo.mirt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by jo on 6/6/2018.
 */
public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s=getIntent().getStringExtra("company_id");
        setContentView(R.layout.activity_scrolling);
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        ImageView fab3 = (ImageView) findViewById(R.id.ivContactItem1);
        String []profile=new String[5];
        DBHelper helping = new DBHelper(this);
        profile=helping.getCompanyProfile(s);
        TextView toptext = (TextView) findViewById(R.id.toptext);
        toptext.setText(profile[0]);
        TextView tvId = (TextView) findViewById(R.id.aboutdesc);
        tvId.setText(profile[0]);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Toast.makeText(ScrollingActivity.this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                        */
                String phoneNo="0910576650";
                String dial = "tel:" + phoneNo;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}