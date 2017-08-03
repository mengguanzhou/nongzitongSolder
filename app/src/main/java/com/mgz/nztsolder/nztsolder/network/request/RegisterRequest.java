package com.mgz.nztsolder.nztsolder.network.request;

/**
 * Created by john on 2017/7/6.
 */

public class RegisterRequest {

    private String phoneNum;
    private String password;
    private String codeNum;
    private int country;
    private String solderName;
    private String address;
    private String telephone;
    private String zhifubao;
    private String weixin;
    private int bankType;
    private String bankCard;

    public RegisterRequest() {}
    public RegisterRequest(String phoneNum, String password, String codeNum, String solderName, int country, String address, String telephone, String zhifubao,
                           String weixin, int bankType, String bankCard) {
        this.phoneNum = phoneNum;
        this.password = password;
        this.codeNum = codeNum;
        this.solderName = solderName;
        this.country = country;
        this.address = address;
        this.telephone = telephone;
        this.zhifubao = zhifubao;
        this.weixin = weixin;
        this.bankType = bankType;
        this.bankCard = bankCard;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getSolderName() {
        return solderName;
    }

    public void setSolderName(String solderName) {
        this.solderName = solderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getZhifubao() {
        return zhifubao;
    }

    public void setZhifubao(String zhifubao) {
        this.zhifubao = zhifubao;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public int getBankType() {
        return bankType;
    }

    public void setBankType(int bankType) {
        this.bankType = bankType;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
}
