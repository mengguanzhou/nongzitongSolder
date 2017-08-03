package com.mgz.nztsolder.nztsolder.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.mgz.nztsolder.nztsolder.R;
import com.mgz.nztsolder.nztsolder.activity.AddGoodsActivity;
import com.mgz.nztsolder.nztsolder.adapter.GoodsListAdapter;
import com.mgz.nztsolder.nztsolder.basictool.BaseFragment;
import com.mgz.nztsolder.nztsolder.data.GoodsData;
import com.mgz.nztsolder.nztsolder.utils.Logger;
import com.mgz.nztsolder.nztsolder.view.PullToRefreshBase;
import com.mgz.nztsolder.nztsolder.view.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by john on 2017/7/21.
 */

public class GoodsFragment extends BaseFragment implements View.OnClickListener {

    private Button menu;
    private Button manage;
    private SlidingMenu slidingMenu;
    private TextView all;
    private LinearLayout allLine;
    private TextView normal;
    private LinearLayout normalLine;
    private TextView zhekou;
    private LinearLayout zhekouLine;
    private TextView manjian;
    private LinearLayout manjianLine;
    private TextView tuangou;
    private LinearLayout tuangouLine;
    private TextView bind;
    private LinearLayout bindLine;
    private GridView gridView;
    private PullToRefreshGridView refreshGridView;
    private GoodsListAdapter adapter;
    private ArrayList<GoodsData> goodsDatas = new ArrayList<GoodsData>();

    private Handler handler = new Handler () {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(), "上拉加载更多", Toast.LENGTH_SHORT).show();
                    refreshGridView.onRefreshComplete();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initView(View view) {
        menu = (Button) view.findViewById(R.id.btn_menu);
        manage = (Button) view.findViewById(R.id.btn_manage);
        slidingMenu = new SlidingMenu(getActivity());

        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.width_90);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.menu_goods);

        all = (TextView) getActivity().findViewById(R.id.tv_all);
        allLine = (LinearLayout) getActivity().findViewById(R.id.layout_all);
        normal = (TextView) getActivity().findViewById(R.id.tv_normal);
        normalLine = (LinearLayout) getActivity().findViewById(R.id.layout_normal);
        zhekou = (TextView) getActivity().findViewById(R.id.tv_zhekou);
        zhekouLine = (LinearLayout) getActivity().findViewById(R.id.layout_zhekou);
        manjian = (TextView) getActivity().findViewById(R.id.tv_manjian);
        manjianLine = (LinearLayout) getActivity().findViewById(R.id.layout_manjian);
        tuangou = (TextView) getActivity().findViewById(R.id.tv_tuangou);
        tuangouLine = (LinearLayout) getActivity().findViewById(R.id.layout_tuangou);
        bind = (TextView) getActivity().findViewById(R.id.tv_bind);
        bindLine = (LinearLayout) getActivity().findViewById(R.id.layout_bind);
        refreshGridView = (PullToRefreshGridView) view.findViewById(R.id.gv_goods);
        refreshGridView.init(PullToRefreshGridView.MODE_PULL_UP_TO_REFRESH);
        gridView = refreshGridView.getRefreshableView();
        refreshGridView.setOnRefreshListener(onRefreshListener);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            GoodsData temp = new GoodsData();
            goodsDatas.add(temp);
        }
        adapter = new GoodsListAdapter(getActivity(), goodsDatas, R.layout.item_goods);
        gridView.setAdapter(adapter);

    }

    @Override
    protected void setOnClickListner() {
        View[] views = new View[] {menu, manage, all, normal, zhekou, manjian, tuangou, bind};
        quickSetClickListener(views, this);
    }

    @Override
    public void onClick(View v) {
        if (v == menu) {
            slidingMenu.showMenu();
        } else if (v == all) {
            if (allLine.getVisibility() == View.GONE) {
                allLine.setVisibility(View.VISIBLE);
            } else {
                allLine.setVisibility(View.GONE);
            }
        } else if (v == manage) {
            showPopupMenu(manage);
        }
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.goods_manage, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        break;
                    case R.id.add:
                        startActivity(new Intent(getActivity(), AddGoodsActivity.class));
                        break;
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });

        popupMenu.show();
    }

    private PullToRefreshBase.OnRefreshListener onRefreshListener = new PullToRefreshBase.OnRefreshListener() {
        @Override
        public void onRefresh(int mode) {
            if (PullToRefreshGridView.MODE_PULL_DOWN_TO_REFRESH == mode) {
                Toast.makeText(getActivity().getApplicationContext(), "下拉刷新", Toast.LENGTH_SHORT).show();
                refreshGridView.onRefreshComplete();
            } else if (mode == PullToRefreshGridView.MODE_PULL_UP_TO_REFRESH) {
                // 加载更多
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            GoodsData temp = new GoodsData();
                            goodsDatas.add(temp);
                        }
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }, 1000);

            }
        }
    };
}
