package com.mgz.nztsolder.nztsolder.basictool;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by john on 2017/7/5.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container,
                false);
        initView(view);
        initData();

        setOnClickListner();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    /**
     * 返回布局文件Id
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化视图
     */
    protected abstract void initView(View view);

    protected void initData() {

    }

    /**
     * 设置监听
     */
    protected void setOnClickListner() {

    }

    protected void quickSetClickListener(View[] views, View.OnClickListener listener) {
        for (int i = 0; i < views.length; i++) {
            views[i].setOnClickListener(listener);
        }
    }

}
