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

public class Tab5Fragment extends Fragment {
    private static final String TAG="Tab5Fragment";
    private WebView funny;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab5_fragment,container,false);
        funny=(WebView)view.findViewById(R.id.funnyView);
        funny.loadUrl("http://www.mirtmirt.com/funny/");
        funny.getSettings().setJavaScriptEnabled(true);
        funny.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        funny.setWebViewClient(new WebViewClient());
        return view;
    }
}
