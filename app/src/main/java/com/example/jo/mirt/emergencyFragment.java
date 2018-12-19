package com.example.jo.mirt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class emergencyFragment extends ListFragment {
    ListView lv;
    ArrayAdapter<String> adapter;
    String[] positions = {"RedCross", "Fire", "Police", "Traffic Police", "Singers", "Nearest Health Station"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                positions);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getActivity(), CompanyAcitivity.class);
        //i.putExtra("comapny_id",position);
        position=5;
        i.putExtra("company_id",position+"");
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}

