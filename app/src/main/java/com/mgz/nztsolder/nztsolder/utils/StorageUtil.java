package com.mgz.nztsolder.nztsolder.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by john on 2017/7/7.
 */

public class StorageUtil {

    private static SharedPreferences sp;

    public static void init(Context context) {
        sp = context.getSharedPreferences("userInfo", 0);
    }

    public static void safeUserInfo(String phoneNum, String password, String solderId) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("phoneNum", phoneNum);
        editor.putString("password", password);
        editor.putString("solderId", solderId);
        editor.commit();
    }
    public static void clearStorage() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static void safeState(boolean state) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", state);
        editor.commit();
    }

    public static String getPhoneNum() {
        return sp.getString("phoneNum", "");
    }
    public static String getPassword() {
        return sp.getString("password","");
    }
    public static boolean getLoginState() {
        return sp.getBoolean("isLogin", false);
    }
    public static String getSolderId() {
        return sp.getString("solderId", "");
    }
}
