package com.mgz.nztsolder.nztsolder.network.reponse;

import java.util.ArrayList;

/**
 * Created by john on 2017/7/19.
 */

public class GetCountryReponse {

    private int success;
    private ArrayList<String> countrys;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<String> getCountrys() {
        return countrys;
    }

    public void setCountrys(ArrayList<String> countrys) {
        this.countrys = countrys;
    }
}
