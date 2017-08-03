package com.mgz.nztsolder.nztsolder.utils;

import com.google.gson.Gson;
import com.mgz.nztsolder.nztsolder.constant.Constants;
import com.mgz.nztsolder.nztsolder.network.HttpStringResponseHandler;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.MediaType;

/**
 * Created by john on 2017/7/5.
 */

public class MyOKHttp {
    public static void postToBase (String method,Object requireObject, HttpStringResponseHandler handler) {
        Gson gson = new Gson();
        String requestContent = gson.toJson(requireObject);
        Logger.e("network", requestContent);
        OkHttpUtils.postString()
                .url(Constants.BaseUrl + "/" + method)
                .content(requestContent)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(handler);
    }
}
