package com.bignews.bignews.GongnengOne;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OKHTTPUtil {

    public static void sendOkHttpRequest(String Url,okhttp3.Callback callback){

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(Url).build();
        client.newCall(request).enqueue(callback);

    }


}