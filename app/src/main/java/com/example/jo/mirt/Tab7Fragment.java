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

public class Tab7Fragment extends Fragment{
    private static final String TAG="Tab5Fragment";
    private WebView top;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab7_fragment,container,false);
        top=(WebView)view.findViewById(R.id.topView);
        top.loadUrl("http://www.mirtmirt.com/category/top-10-videos/");
        top.getSettings().setJavaScriptEnabled(true);
        top.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        top.setWebViewClient(new WebViewClient());
        return view;
    }
}
