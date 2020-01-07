//package com.trampcr.developerrepository.network.okhttp;
//
//import android.util.Log;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// *
// * @author trampcr
// * @date 2019/9/18
// */
//
//public class OkHttpDemo {
//    public static final String TAG = OkHttpDemo.class.getSimpleName();
//    public static final String GET_URL = "https://raw.github.com/square/okhttp/master/README.md";
//    public static final String POST_URL = "http://www.roundsapp.com/post";
//    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
//
//    public void get(String url) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder().url(url).build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                Log.d(TAG, "onResponse: result = " + result);
//            }
//        });
//    }
//
//    public void post(String url, String json) {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody body = RequestBody.create(JSON, json);
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                Log.d(TAG, "onResponse: post result = " + result);
//            }
//        });
//    }
//
//    public String bowlingJson(String player1, String player2) {
//        return "{'winCondition':'HIGH_SCORE',"
//                + "'name':'Bowling',"
//                + "'round':4,"
//                + "'lastSaved':1367702411696,"
//                + "'dateStarted':1367702378785,"
//                + "'players':["
//                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
//                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
//                + "]}";
//    }
//}
