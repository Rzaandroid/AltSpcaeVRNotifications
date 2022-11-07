package com.example.altspcaevrnotifications;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText ed1;

    private WebView wv1;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //b1 = (Button) findViewById(R.id.button);

        wv1 = (WebView) findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.addJavascriptInterface(new WebViewJavaScriptInterface(this), "Android");
        wv1.addJavascriptInterface(new WebViewJavaScriptInterface(this), "HtmlViewer");
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl("https://account.altvr.com/users/sign_in");
        wv1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                WebView.HitTestResult hr = ((WebView) v).getHitTestResult();

                Log.i("TEST", "getExtra = " + hr.getExtra() + "\t\t Type=" + hr.getType());
                return false;
            }
        });
/*
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ed1.getText().toString();


                Log.e("TEST", wv1.getUrl());
                //wv1.loadUrl(url);
            }
        });
        */
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Log.e("TEST", view.getUrl());
            /*
            if (view.getUrl() != "https://account.altvr.com/users/sign_in") {
                while (true) {
                    wv1.loadUrl("https://account.altvr.com/friendships/online");
                    Log.e("TEST", view.getUrl());
                    wv1.loadUrl("https://account.altvr.com/events/interested");
                    Log.e("TEST", view.getUrl());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            */
            return true;
        }
    }

    public class WebViewJavaScriptInterface {

        private Context context;


        public WebViewJavaScriptInterface(Context context) {
            this.context = context;
        }

        public void showMessage(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}