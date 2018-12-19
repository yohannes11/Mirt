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

public class Tab3Fragment extends Fragment {
    private static final String TAG="Tab3Fragment";
    private WebView people;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab3_fragment,container,false);
        people=(WebView)view.findViewById(R.id.peopleView);
        people.loadUrl("http://www.mirtmirt.com/people/");
        people.getSettings().setJavaScriptEnabled(true);
        people.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        people.setWebViewClient(new WebViewClient());
        return view;
    }
}
