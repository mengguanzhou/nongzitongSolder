package com.mgz.nztsolder.nztsolder.adapter;

import android.content.Context;

import com.mgz.nztsolder.nztsolder.data.GoodsData;
import com.mtxc.universallistview.UniversalAdapter;
import com.mtxc.universallistview.ViewHolder;

import java.util.List;

/**
 * Created by john on 2017/7/20.
 */

public class GoodsListAdapter extends UniversalAdapter<GoodsData> {

    public GoodsListAdapter(Context context, List<GoodsData> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void updateItem(ViewHolder viewHolder, GoodsData goodsData) {

    }
}
