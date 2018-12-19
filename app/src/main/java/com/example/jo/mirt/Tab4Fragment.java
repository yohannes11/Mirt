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

public class Tab4Fragment extends Fragment {
    private static final String TAG="Tab4Fragment";
    private WebView culture;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab4_fragment,container,false);
        culture=(WebView)view.findViewById(R.id.cultureView);
        culture.loadUrl("http://www.mirtmirt.com/culture/");
        culture.getSettings().setJavaScriptEnabled(true);
        culture.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        culture.setWebViewClient(new WebViewClient());
        return view;
    }
}
