package com.mgz.nztsolder.nztsolder.network.reponse;

import java.util.ArrayList;

/**
 * Created by john on 2017/7/28.
 */

public class CategoryResponse {

    private ArrayList<String> categoryList;
    private int success;

    public ArrayList<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<String> categoryList) {
        this.categoryList = categoryList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
