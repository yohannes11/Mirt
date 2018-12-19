package com.example.jo.mirt;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;

/**
 * Created by jo on 3/23/2018.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG="Tab1Fragment";
    private ProgressDialog progress;
    private WebView home;
    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.tab1_fragment,container,false);
          spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        home=(WebView)view.findViewById(R.id.homeview);
        home.loadUrl("http://www.mirtmirt.com/");
        home.getSettings().setJavaScriptEnabled(true);
        home.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (progress != null)
                    progress.dismiss();
            }
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    home.stopLoading();
                } catch (Exception e) {
                }
                if (home.canGoBack()) {
                    home.goBack();
                }
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                home.loadUrl("about:blank");
                home.setFocusableInTouchMode(false);
                home.setFocusable(false);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("NO INTERNET CONNECTION");
                alertDialog.setMessage("Check your internet connection and try again.");
                alertDialog.setButton(BUTTON_NEGATIVE,"Go to profile",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(getActivity(),
                                Main2Activity.class);
                        startActivity(myIntent);
                    }
                });
                alertDialog.show();
                alertDialog.getButton(BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }
        });
        return home;
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }
    }
}