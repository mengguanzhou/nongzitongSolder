package com.mgz.nztsolder.nztsolder.utils;

import android.content.Context;

/**
 * Created by john on 2017/7/5.
 */

public class ProgressDialog {

    private CustomProgressDialog progressDialog = null;

    private ProgressDialog() {
    }

    private static ProgressDialog pd = null;

    public static ProgressDialog getInstance() {
        if (pd == null) {
            pd = new ProgressDialog();
        }
        return pd;
    }

    public void startProgressDialog(Context context, String message) {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(context);
            progressDialog.setMessage(message);
        }

        progressDialog.show();
    }

    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
