package com.mgz.nztsolder.nztsolder.network.reponse;

/**
 * Created by john on 2017/7/28.
 */

public class AddGoodsResponse {
    private String goodsId;
    private int success;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
