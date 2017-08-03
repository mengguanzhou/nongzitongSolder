package com.mgz.nztsolder.nztsolder.network.reponse;

import java.util.ArrayList;

/**
 * Created by john on 2017/7/19.
 */

public class GetCityReponse {

    private int success;
    private ArrayList<String> citys;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<String> getCitys() {
        return citys;
    }

    public void setCitys(ArrayList<String> citys) {
        this.citys = citys;
    }
}
