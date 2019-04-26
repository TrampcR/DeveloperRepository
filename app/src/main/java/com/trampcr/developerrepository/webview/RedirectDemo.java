package com.trampcr.developerrepository.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by trampcr on 2018/12/6.
 */

public class RedirectDemo {
    public static final String TAG = RedirectDemo.class.getSimpleName();

    public static void getRedirectAddress(Context context, String url) {
        if (context == null || TextUtils.isEmpty(url)) {
            return;
        }

        WebView webView = new WebView(context);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            //页面加载开始
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "onPageStarted: url = " + url);
            }

            //页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //这个realUrl即为重定向之后的地址
                String realUrl = url;
                Log.d(TAG, "onPageFinished: realUrl = " + realUrl);
            }
        });
    }
}
