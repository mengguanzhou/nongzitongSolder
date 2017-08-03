package com.mgz.nztsolder.nztsolder.activity;


import android.view.View;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mgz.nztsolder.nztsolder.R;
import com.mgz.nztsolder.nztsolder.basictool.BaseActivity;
import com.mgz.nztsolder.nztsolder.fragment.GoodsFragment;
import com.mgz.nztsolder.nztsolder.fragment.OrderFragment;

import android.support.v4.app.FragmentTransaction;

/**
 * Created by john on 2017/7/20.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private TextView goods;
    private TextView orders;
    private SlidingMenu slidingMenu;
    private GoodsFragment goodsFragment;
    private OrderFragment orderFragment;

    @Override
    public int getRecoureId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        goodsFragment = new GoodsFragment();
        orderFragment = new OrderFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_home, goodsFragment).add(R.id.fl_home, orderFragment).hide(orderFragment).show(goodsFragment).commit();
        goods = (TextView) findViewById(R.id.tv_goods);
        orders = (TextView) findViewById(R.id.tv_orders);
    }

    @Override
    public void setOnClickListner() {
        goods.setOnClickListener(this);
        orders.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == goods) {
            getSupportFragmentManager().beginTransaction().hide(orderFragment).show(goodsFragment).commit();
        } else if (v == orders) {
            getSupportFragmentManager().beginTransaction().hide(goodsFragment).show(orderFragment).commit();
        }
    }
}
