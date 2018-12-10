package com.bignews.bignews.GongnengOne;

import okhttp3.Response;

public interface HTTPCallBackListener {
    void onFinish(String response);
    void onError(Exception e);

}