package com.trampcr.developerrepository;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.trampcr.developerrepository.customview.CameraAnimationView;
import com.trampcr.developerrepository.customview.drag.DragHelperActivity;
import com.trampcr.developerrepository.customview.drag.DragListenerActivity;
import com.trampcr.developerrepository.customview.drag.DragToCollectActivity;
import com.trampcr.developerrepository.customview.drag.DragUpDownActivity;
import com.trampcr.developerrepository.customview.point.PointTypeEvaluator;
import com.trampcr.developerrepository.customview.point.PointView;
import com.trampcr.developerrepository.customview.province.ProvinceTypeEvaluator;
import com.trampcr.developerrepository.customview.province.ProvinceView;
import com.trampcr.developerrepository.customview.touch.CooperateMultiTouchActivity;
import com.trampcr.developerrepository.customview.touch.RelayMultiTouchActivity;
import com.trampcr.developerrepository.customview.touch.SelfMultiTouchActivity;
import com.trampcr.developerrepository.customview.touch.TouchView;
import com.trampcr.developerrepository.customview.touch.dispatchdemo.ViewGroupDispatchActivity;
import com.trampcr.developerrepository.customview.viewpager.TwoPagerActivity;
import com.trampcr.developerrepository.imagerepo.GlideActivity;
import com.trampcr.developerrepository.imagerepo.PicassoActivity;
import com.trampcr.developerrepository.ipc.aidl.BookManagerActivity;
import com.trampcr.developerrepository.ipc.contentprovider.ContentProviderActivity;
import com.trampcr.developerrepository.ipc.messenger.MessengerActivity;
import com.trampcr.developerrepository.list.ForRemove;
import com.trampcr.developerrepository.listview.ArrayAdapterActivity;
import com.trampcr.developerrepository.listview.BaseAdapterActivity;
import com.trampcr.developerrepository.listview.SimpleAdapterActivity;
import com.trampcr.developerrepository.proxy.DynamicProxyActivity;
import com.trampcr.developerrepository.proxy.dynamic.DynamicProxyDemo;
import com.trampcr.developerrepository.reflect.Person;
import com.trampcr.developerrepository.reflect.ReflectActivity;
import com.trampcr.developerrepository.reflect.ReflectHelper;
import com.trampcr.developerrepository.retrofitdemo.GetRequestIcibaActivity;
import com.trampcr.developerrepository.retrofitdemo.PostRequestYouDaoActivity;
import com.trampcr.developerrepository.rxjava.RxJavaActivity;
import com.trampcr.developerrepository.thread.asynctask.AsyncTaskActivity;
import com.trampcr.developerrepository.thread.handlerthread.HandlerThreadActivity;
import com.trampcr.developerrepository.thread.intentservice.IntentServiceActivity;
import com.trampcr.developerrepository.thread.threadlocal.ThreadLocalActivity;
import com.trampcr.developerrepository.utils.CommonUtils;
import com.trampcr.developerrepository.utils.DimenUtils;
import com.trampcr.developerrepository.webview.RedirectDemo;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.view.KeyEvent.KEYCODE_BACK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String BAIDU_URL = "http://www.baidu.com";
    public static final String WEBVIEW_TEST_URL = "https://liebao.winndoo.com/api/v1/searchResult?kwd=%E7%8E%8B%E8%AF%97%E9%BE%84%E6%89%8B%E9%93%BE&unionid=d6cf096a6f11ca58b28ef9b2be851e24&language=zh_CN&isinstall=false";
    public static final String ASSETS_URL = "file:///android_asset/synchronized.html";
    public static final String CLASS_NAME = "com.trampcr.developerrepository.reflect.Person";
    public static final float TRANSLATE_WIDTH = DimenUtils.dp2px(300);

    public String sdcardUrl;

    private Button mBtnWebViewDemo;
    private Button mBtnStartActivityDemo;
    private Button mBtnReflectClick;
    private Button mBtnViewGroupDispatch;
    private Button mBtnRelayMultiTouchView;
    private Button mBtnCooperateMultiTouchView;
    private Button mBtnSelfMultiTouchView;
    private Button mBtnTwoPagerView;
    private Button mBtnDragListenerGridView;
    private Button mBtnDragHelperGridView;
    private Button mBtnDragToCollectView;
    private Button mBtnDragUpDownView;
    private Button mBtnAsyncTaskView;
    private Button mBtnHandlerThreadView;
    private Button mBtnIntentServiceView;
    private Button mBtnThreadLocalView;
    private Button mBtnListArrayAdapterView;
    private Button mBtnListSimpleAdapterView;
    private Button mBtnListBaseAdapterView;
    private Button mBtnGetRequestIcibaView;
    private Button mBtnPostRequestYoudaoView;
    private Button mBtnPicassoView;
    private Button mBtnGlideView;
    private Button mBtnReflectView;
    private Button mBtnDynamicProxyView;
    private Button mBtnRxJavaView;
    private Button mBtnMessengerView;
    private Button mBtnAIDLView;
    private Button mBtnContentProviderView;

    private WebView mWebView;
    private Handler mHandler;

    private CameraAnimationView mCameraAnimationView;
    private ImageView mAnimatorView;
    private PointView mPointView;
    private ProvinceView mProvinceView;
    private TouchView mTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initData();
        initListener();
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
//        testCameraAnimationView();
//        testAnimatorView();
//        testPointView();
//        testProvinceView();
//        testTouchView();
//        testOkhttp();
//        new DynamicProxyDemo().testDynamicProxy();
    }

    private void testTouchView() {
//        mTouchView = (TouchView) findViewById(R.id.view);
        mTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "touch", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testProvinceView() {
//        mProvinceView = (ProvinceView) findViewById(R.id.view);

        ObjectAnimator provinceAnimator = ObjectAnimator.ofObject(mProvinceView, "province", new ProvinceTypeEvaluator(), "澳门");
        provinceAnimator.setStartDelay(1000);
        provinceAnimator.setDuration(3000);
        provinceAnimator.start();
    }

    private void testPointView() {
//        mPointView = (PointView) findViewById(R.id.view);

        Point targetPoint = new Point((int) DimenUtils.dp2px(200), (int) DimenUtils.dp2px(200));
        ObjectAnimator pointAnimator = ObjectAnimator.ofObject(mPointView, "point", new PointTypeEvaluator(), targetPoint);
        pointAnimator.setStartDelay(1000);
        pointAnimator.setDuration(2000);
        pointAnimator.start();
    }

    private void testAnimatorView() {
//        mAnimatorView = (ImageView) findViewById(R.id.view);

        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.6f * TRANSLATE_WIDTH);
        Keyframe keyframe3 = Keyframe.ofFloat(0.6f, 0.8f * TRANSLATE_WIDTH);
        Keyframe keyframe4 = Keyframe.ofFloat(1, 1 * TRANSLATE_WIDTH);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2,
                keyframe3, keyframe4);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mAnimatorView, propertyValuesHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    private void testCameraAnimationView() {
//        mCameraAnimationView = (CameraAnimationView) findViewById(R.id.view);

        // 动画依次执行
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofInt(mCameraAnimationView, "topFlip", -45);
//        topFlipAnimator.setDuration(1500);
//
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofInt(mCameraAnimationView, "bottomFlip", 45);
//        topFlipAnimator.setDuration(1500);
//
//        ObjectAnimator rotateAnimator = ObjectAnimator.ofInt(mCameraAnimationView, "rotate", 270);
//        rotateAnimator.setDuration(1500);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(bottomFlipAnimator, rotateAnimator, topFlipAnimator);
//        animatorSet.setStartDelay(1000);
//        animatorSet.start();

        // 动画同时执行
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofInt("topFlip", -45);
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofInt("rotate", 270);
        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofInt("bottomFlip", 45);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mCameraAnimationView, topFlipHolder,
                rotationHolder, bottomFlipHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
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
//        mBtnWebViewDemo = (Button) findViewById(R.id.btn_webview_demo);
//        mBtnStartActivityDemo = (Button) findViewById(R.id.btn_start_activity_demo);
//        mBtnReflectClick = (Button) findViewById(R.id.btn_reflect_click);
//        mWebView = (WebView) findViewById(R.id.web_view);

        mBtnViewGroupDispatch = (Button) findViewById(R.id.btn_view_group_dispatch);
        mBtnRelayMultiTouchView = (Button) findViewById(R.id.btn_relay_multi_touch_view);
        mBtnCooperateMultiTouchView = (Button) findViewById(R.id.btn_cooperate_multi_touch_view);
        mBtnSelfMultiTouchView = (Button) findViewById(R.id.btn_self_multi_touch_view);
        mBtnTwoPagerView = (Button) findViewById(R.id.btn_two_pager_view);
        mBtnDragListenerGridView = (Button) findViewById(R.id.btn_drag_listener_grid_view);
        mBtnDragHelperGridView = (Button) findViewById(R.id.btn_drag_helper_grid_view);
        mBtnDragToCollectView = (Button) findViewById(R.id.btn_drag_to_collect_view);
        mBtnDragUpDownView = (Button) findViewById(R.id.btn_drag_up_down_view);
        mBtnAsyncTaskView = (Button) findViewById(R.id.btn_async_task_view);
        mBtnHandlerThreadView = (Button) findViewById(R.id.btn_handler_thread_view);
        mBtnIntentServiceView = (Button) findViewById(R.id.btn_intent_service_view);
        mBtnThreadLocalView = (Button) findViewById(R.id.btn_thread_local_view);
        mBtnListArrayAdapterView = (Button) findViewById(R.id.btn_list_array_adapter_view);
        mBtnListSimpleAdapterView = (Button) findViewById(R.id.btn_list_simple_adapter_view);
        mBtnListBaseAdapterView = (Button) findViewById(R.id.btn_list_base_adapter_view);
        mBtnGetRequestIcibaView = (Button) findViewById(R.id.btn_get_request_iciba_view);
        mBtnPostRequestYoudaoView = (Button) findViewById(R.id.btn_post_request_youdao_view);
        mBtnPicassoView = (Button) findViewById(R.id.btn_picasso_view);
        mBtnGlideView = (Button) findViewById(R.id.btn_glide_view);
        mBtnReflectView = (Button) findViewById(R.id.btn_reflect_view);
        mBtnDynamicProxyView = (Button) findViewById(R.id.btn_dynamic_proxy_view);
        mBtnRxJavaView = (Button) findViewById(R.id.btn_rx_java_view);
        mBtnMessengerView = (Button) findViewById(R.id.btn_messenger_view);
        mBtnAIDLView = (Button) findViewById(R.id.btn_aidl_view);
        mBtnContentProviderView = (Button) findViewById(R.id.btn_content_provider_view);
    }

    private void initListener() {
//        mBtnWebViewDemo.setOnClickListener(this);
//        mBtnStartActivityDemo.setOnClickListener(this);
//        mBtnReflectClick.setOnClickListener(this);

        mBtnViewGroupDispatch.setOnClickListener(this);
        mBtnRelayMultiTouchView.setOnClickListener(this);
        mBtnCooperateMultiTouchView.setOnClickListener(this);
        mBtnSelfMultiTouchView.setOnClickListener(this);
        mBtnTwoPagerView.setOnClickListener(this);
        mBtnDragListenerGridView.setOnClickListener(this);
        mBtnDragHelperGridView.setOnClickListener(this);
        mBtnDragToCollectView.setOnClickListener(this);
        mBtnDragUpDownView.setOnClickListener(this);
        mBtnAsyncTaskView.setOnClickListener(this);
        mBtnHandlerThreadView.setOnClickListener(this);
        mBtnIntentServiceView.setOnClickListener(this);
        mBtnThreadLocalView.setOnClickListener(this);
        mBtnListArrayAdapterView.setOnClickListener(this);
        mBtnListSimpleAdapterView.setOnClickListener(this);
        mBtnListBaseAdapterView.setOnClickListener(this);
        mBtnGetRequestIcibaView.setOnClickListener(this);
        mBtnPostRequestYoudaoView.setOnClickListener(this);
        mBtnPicassoView.setOnClickListener(this);
        mBtnGlideView.setOnClickListener(this);
        mBtnReflectView.setOnClickListener(this);
        mBtnDynamicProxyView.setOnClickListener(this);
        mBtnRxJavaView.setOnClickListener(this);
        mBtnMessengerView.setOnClickListener(this);
        mBtnAIDLView.setOnClickListener(this);
        mBtnContentProviderView.setOnClickListener(this);
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
//        OkHttpDemo okHttpDemo = new OkHttpDemo();
//        okHttpDemo.get(OkHttpDemo.GET_URL);
//        String json = okHttpDemo.bowlingJson("lfy", "zxm");
//        okHttpDemo.post(OkHttpDemo.POST_URL, json);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_webview_demo:
//                testWebView();
//                break;
//            case R.id.btn_start_activity_demo:
//                final Intent intent = new Intent(this, StartActivityDemo.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                startActivity(intent);
//                try {
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                Log.d(TAG, "run: start");
//                                PendingIntent.getActivity(MainActivity.this, 0, intent, 0).send();
//                            } catch (PendingIntent.CanceledException e) {
//                                e.printStackTrace();
//                            }
////                            startActivity(intent);
//                        }
//                    },5000);
//
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//            case R.id.btn_reflect_click:
//                ReflectHelper.hookOnClickListener(v);
            case R.id.btn_view_group_dispatch:
                CommonUtils.startActivity(this, ViewGroupDispatchActivity.class);
                break;
            case R.id.btn_relay_multi_touch_view:
                CommonUtils.startActivity(this, RelayMultiTouchActivity.class);
                break;
            case R.id.btn_cooperate_multi_touch_view:
                CommonUtils.startActivity(this, CooperateMultiTouchActivity.class);
                break;
            case R.id.btn_self_multi_touch_view:
                CommonUtils.startActivity(this, SelfMultiTouchActivity.class);
                break;
            case R.id.btn_two_pager_view:
                CommonUtils.startActivity(this, TwoPagerActivity.class);
                break;
            case R.id.btn_drag_listener_grid_view:
                CommonUtils.startActivity(this, DragListenerActivity.class);
                break;
            case R.id.btn_drag_helper_grid_view:
                CommonUtils.startActivity(this, DragHelperActivity.class);
                break;
            case R.id.btn_drag_to_collect_view:
                CommonUtils.startActivity(this, DragToCollectActivity.class);
                break;
            case R.id.btn_drag_up_down_view:
                CommonUtils.startActivity(this, DragUpDownActivity.class);
                break;
            case R.id.btn_async_task_view:
                CommonUtils.startActivity(this, AsyncTaskActivity.class);
                break;
            case R.id.btn_handler_thread_view:
                CommonUtils.startActivity(this, HandlerThreadActivity.class);
                break;
            case R.id.btn_intent_service_view:
                CommonUtils.startActivity(this, IntentServiceActivity.class);
                break;
            case R.id.btn_thread_local_view:
                CommonUtils.startActivity(this, ThreadLocalActivity.class);
                break;
            case R.id.btn_list_array_adapter_view:
                CommonUtils.startActivity(this, ArrayAdapterActivity.class);
                break;
            case R.id.btn_list_simple_adapter_view:
                CommonUtils.startActivity(this, SimpleAdapterActivity.class);
                break;
            case R.id.btn_list_base_adapter_view:
                CommonUtils.startActivity(this, BaseAdapterActivity.class);
                break;
            case R.id.btn_get_request_iciba_view:
                CommonUtils.startActivity(this, GetRequestIcibaActivity.class);
                break;
            case R.id.btn_post_request_youdao_view:
                CommonUtils.startActivity(this, PostRequestYouDaoActivity.class);
                break;
            case R.id.btn_picasso_view:
                CommonUtils.startActivity(this, PicassoActivity.class);
                break;
            case R.id.btn_glide_view:
                CommonUtils.startActivity(this, GlideActivity.class);
                break;
            case R.id.btn_reflect_view:
                CommonUtils.startActivity(this, ReflectActivity.class);
                break;
            case R.id.btn_dynamic_proxy_view:
                CommonUtils.startActivity(this, DynamicProxyActivity.class);
                break;
            case R.id.btn_rx_java_view:
                CommonUtils.startActivity(this, RxJavaActivity.class);
                break;
            case R.id.btn_messenger_view:
                CommonUtils.startActivity(this, MessengerActivity.class);
                break;
            case R.id.btn_aidl_view:
                CommonUtils.startActivity(this, BookManagerActivity.class);
                break;
            case R.id.btn_content_provider_view:
                CommonUtils.startActivity(this, ContentProviderActivity.class);
                break;
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
