package com.mgz.nztsolder.nztsolder;

import android.app.Application;

import com.mgz.nztsolder.nztsolder.utils.StorageUtil;

/**
 * Created by john on 2017/7/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StorageUtil.init(getApplicationContext());
    }
}