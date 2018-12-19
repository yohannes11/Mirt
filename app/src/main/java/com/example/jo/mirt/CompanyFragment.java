package com.example.jo.mirt;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CompanyFragment extends ListFragment {
    ListView lv;
    ArrayAdapter<String> adapter;


    String[] categories;
    String[] indexes;
    String[] test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DBHelper helping = new DBHelper(this.getContext());
        int category_count=helping.getProfilesCount("categories");
        categories=new String[category_count];
        indexes =new String[category_count];
        Cursor stockList=helping.getAllcategories();
        int count=0;
        while(stockList.isAfterLast() == false){

            categories[count]=stockList.getString(stockList.getColumnIndex("name" ));
            indexes[count]=stockList.getString(stockList.getColumnIndex("id" ));
            stockList.moveToNext();
            count++;
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                categories);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


            @Override
            public void onListItemClick (ListView l, View v,int position, long id){
                Intent i = new Intent(getActivity(), CompanyAcitivity.class);
                //i.putExtra("comapny_id",position);
                String link=indexes[position];
                i.putExtra("company_id", link);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        }