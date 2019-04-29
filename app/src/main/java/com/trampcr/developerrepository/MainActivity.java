package com.trampcr.developerrepository;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.trampcr.developerrepository.customview.AnimationView;
import com.trampcr.developerrepository.customview.CameraView;
import com.trampcr.developerrepository.list.ForRemove;
import com.trampcr.developerrepository.reflect.Person;
import com.trampcr.developerrepository.reflect.ReflectHelper;
import com.trampcr.developerrepository.retrofitdemo.GitHubService;
import com.trampcr.developerrepository.retrofitdemo.Repo;
import com.trampcr.developerrepository.startactivity.StartActivityDemo;
import com.trampcr.developerrepository.webview.RedirectDemo;
import com.trampcr.developerrepository.webview.WebViewDemo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String BAIDU_URL = "http://www.baidu.com";
    public static final String WEBVIEW_TEST_URL = "https://liebao.winndoo.com/api/v1/searchResult?kwd=%E7%8E%8B%E8%AF%97%E9%BE%84%E6%89%8B%E9%93%BE&unionid=d6cf096a6f11ca58b28ef9b2be851e24&language=zh_CN&isinstall=false";
    public static final String ASSETS_URL = "file:///android_asset/synchronized.html";
    public static final String CLASS_NAME = "com.trampcr.developerrepository.reflect.Person";

    public String sdcardUrl;

    private Button mBtnWebViewDemo;
    private Button mBtnStartActivityDemo;
    private Button mBtnReflectClick;
    private WebView mWebView;
    private Handler mHandler;

    private AnimationView mAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_view);
//        initView();
//        initData();
//        initListener();
//        init();
//        Logan.w("test logan", 1);
//        Log.d("zxm", "test logan 2");
//        test();
//        testDemos();
//        testRetrofit();
//        testReflect();
//        Log.d(TAG, "onCreate: " + (-1 % 1));
//        testWindow();
//        testRemove();
        testAnimationView();
    }

    private void testAnimationView() {
        mAnimationView = (AnimationView) findViewById(R.id.view);
        ObjectAnimator topFlipAnimator = ObjectAnimator.ofInt(mAnimationView, "topFlip", -30);
        topFlipAnimator.setDuration(1500);

        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofInt(mAnimationView, "bottomFlip", -45);
        topFlipAnimator.setDuration(1500);

        ObjectAnimator rotateAnimator = ObjectAnimator.ofInt(mAnimationView, "rotate", 270);
        rotateAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(topFlipAnimator, rotateAnimator, bottomFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();
    }

    private void testRemove() {
        ForRemove.remove();
    }

    private void testWindow() {
    }

    private void testReflect() {
        ReflectHelper.printConstructor(CLASS_NAME);
        Constructor constructor = ReflectHelper.getConstructor(CLASS_NAME, String.class, Integer.class);
        if (constructor == null) {
            return;
        }

        try {
            Object object = constructor.newInstance("China", 12);
            Person person = (Person) object;
            Log.d(TAG, "testReflect: person = " + person.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ReflectHelper.printField(CLASS_NAME);
        Field field = ReflectHelper.getField(CLASS_NAME, "age");
        Person person = new Person("China", 12);
        try {
            Integer integer = (Integer) field.get(person);
            Log.d(TAG, "testReflect: integer = " + integer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ReflectHelper.printMethod(CLASS_NAME);
        Person personMethod = new Person();
        Method declaredMethod = ReflectHelper.getMethod(CLASS_NAME, "setCountry", String.class);
        if (declaredMethod == null) {
            return;
        }

        try {
            Object object = declaredMethod.invoke(personMethod, "China");
            String country = person.country;
            Log.d(ReflectHelper.TAG, "testReflect: country = " + country);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ReflectHelper.printArray(new String[]{"1", "2", "trampcr", "园子蛋"});

        ReflectHelper.getGenericType();

        try {
            ReflectHelper.hookClipboardManager(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mHandler = new Handler(Looper.getMainLooper());
        sdcardUrl = "file://" + getExternalFilesDir("").getAbsolutePath() + "/memory_opt.html";
//        sdcardUrl = "content://com.android.htmlfileprovider/storage/emulated/0/memory_opt.html";
        Log.d(TAG, "initData: sdcardUrl = " + sdcardUrl);
    }

    private void initView() {
        mBtnWebViewDemo = (Button) findViewById(R.id.btn_webview_demo);
        mBtnStartActivityDemo = (Button) findViewById(R.id.btn_start_activity_demo);
        mBtnReflectClick = (Button) findViewById(R.id.btn_reflect_click);
        mWebView = (WebView) findViewById(R.id.web_view);
    }

    private void initListener() {
        mBtnWebViewDemo.setOnClickListener(this);
        mBtnStartActivityDemo.setOnClickListener(this);
        mBtnReflectClick.setOnClickListener(this);
    }

    private void testWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());//设置这个属性，可以直接跳当前 WebView，不需要跳浏览器
        mWebView.loadUrl(WEBVIEW_TEST_URL);//加载一个网页
//        mWebView.loadUrl(BAIDU_URL);//加载一个网页
//        mWebView.loadUrl(ASSETS_URL);//加载 assets 目录下的 html 文件
//        mWebView.loadUrl(sdcardUrl);//加载 sdcard 下该应用目录下的 html 文件，直接访问 sdcard 根目录下的文件没成功，可能是因为没有存储权限
    }

    private void test() {
        int code = 255;
        String codeStr = String.valueOf(code);
        Log.d(TAG, "test: code = " + code + ", codeStr = " + codeStr);
    }

    private void init() {
        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())
                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
                        + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
    }

    private void testDemos() {
        getRedirectAddress();
    }

    private void getRedirectAddress() {
        String pinduoduoUrl = "https://lp.pinduoduo.com/template" +
                ".html?type=4&id=7516&src=liebao&acc=&campaign=1203001luodiye&ad=";
        String apkUrl = "http://mcdn.yangkeduo.com/android_dev/2018-12-05/7513068f5d7136cab1557810ce47af38.apk";
        RedirectDemo.getRedirectAddress(this, apkUrl);
    }

    private void testOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {

            }
        });
    }

    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
//                .addConverterFactory()
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Repo>> call = gitHubService.listRepos("octocat");
        try {
            List<Repo> repos = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_webview_demo:
                testWebView();
                break;
            case R.id.btn_start_activity_demo:
                final Intent intent = new Intent(this, StartActivityDemo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                try {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d(TAG, "run: start");
                                PendingIntent.getActivity(MainActivity.this, 0, intent, 0).send();
                            } catch (PendingIntent.CanceledException e) {
                                e.printStackTrace();
                            }
//                            startActivity(intent);
                        }
                    },5000);

                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_reflect_click:
                ReflectHelper.hookOnClickListener(v);
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
