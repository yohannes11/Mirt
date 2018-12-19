package com.example.jo.mirt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by jo on 3/23/2018.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG="Tab2Fragment";
    private WebView entertainment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab2_fragment,container,false);
        entertainment=(WebView)view.findViewById(R.id.entertainmentView);
        entertainment.loadUrl("http://www.mirtmirt.com/entertainment/");
        entertainment.getSettings().setJavaScriptEnabled(true);
        entertainment.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        entertainment.setWebViewClient(new WebViewClient());
        return view;
    }
}