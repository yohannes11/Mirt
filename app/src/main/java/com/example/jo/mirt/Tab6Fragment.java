package com.example.jo.mirt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jo on 3/23/2018.
 */

public class Tab6Fragment extends Fragment {
    private static final String TAG="Tab5Fragment";
    private WebView sport;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab6_fragment,container,false);
        sport=(WebView)view.findViewById(R.id.sportView);
        sport.loadUrl("http://www.mirtmirt.com/sport/");
        sport.getSettings().setJavaScriptEnabled(true);
        sport.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        sport.setWebViewClient(new WebViewClient());
        return view;
    }
}
