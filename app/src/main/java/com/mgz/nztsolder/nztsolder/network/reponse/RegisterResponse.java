package com.mgz.nztsolder.nztsolder.network.reponse;

/**
 * Created by john on 2017/7/6.
 */

public class RegisterResponse {

    private String solderId;
    private int success;

    public RegisterResponse() {

    }
    public RegisterResponse(String solderId, int success) {
        this.solderId = solderId;
        this.success = success;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getSolderId() {
        return solderId;
    }

    public void setSolderId(String solderId) {
        this.solderId = solderId;
    }
}
