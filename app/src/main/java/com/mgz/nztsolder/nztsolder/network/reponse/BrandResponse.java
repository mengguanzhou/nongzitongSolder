package com.mgz.nztsolder.nztsolder.network.reponse;

import java.util.ArrayList;

/**
 * Created by john on 2017/7/28.
 */

public class BrandResponse {
    private ArrayList<String> brandList;
    private int success;

    public ArrayList<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(ArrayList<String> brandList) {
        this.brandList = brandList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
