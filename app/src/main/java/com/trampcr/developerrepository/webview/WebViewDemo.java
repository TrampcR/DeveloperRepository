package com.trampcr.developerrepository.webview;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by trampcr on 2019/1/29.
 */

public class WebViewDemo {

    public static void loadWebView(Context context) {
        WebView webView = new WebView(context);
//        webView.loadUrl("http://www.baidu.com");//打开具有浏览器功能的软件，例如浏览器、网易云音乐等
        webView.loadUrl("file:///android_asset/synchronized.html");
    }
}
