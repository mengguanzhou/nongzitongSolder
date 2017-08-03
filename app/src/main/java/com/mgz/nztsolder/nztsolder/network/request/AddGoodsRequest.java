package com.mgz.nztsolder.nztsolder.network.request;

/**
 * Created by john on 2017/7/28.
 */

public class AddGoodsRequest {
    private String solderId;
    private int type;
    private String name;
    private double price;
    private double weight;
    private double avgPrice;
    private int length;
    private int height;
    private int width;
    private int category;
    private int brand;
    private String otherBrand;
    private String scanId;
    private String describe;
    private String img;

    public AddGoodsRequest() {}
    public AddGoodsRequest(String solderId, int type, String name, double price, double weight, double avgPrice, int length,
                           int height, int width, int category, int brand, String otherBrand, String scanId, String describe, String img) {
        this.solderId = solderId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.avgPrice = avgPrice;
        this.length = length;
        this.height = height;
        this.width = width;
        this.category = category;
        this.brand = brand;
        this.otherBrand = otherBrand;
        this.scanId = scanId;
        this.describe = describe;
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public String getOtherBrand() {
        return otherBrand;
    }

    public void setOtherBrand(String otherBrand) {
        this.otherBrand = otherBrand;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSolderId() {
        return solderId;
    }

    public void setSolderId(String solderId) {
        this.solderId = solderId;
    }
}
