package com.apps.akkaber.model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String product_id;
    private String type_id;
    private String size_id;
    private String price_before;
    private String value;
    private String price_after;
    private String sub_title;
    private String id;
    private String photo;
    private String title;
    private String category_id;
    private String desc;
    private String price;

    public String getProduct_id() {
        return product_id;
    }

    public String getType_id() {
        return type_id;
    }

    public String getSize_id() {
        return size_id;
    }

    public String getPrice_before() {
        return price_before;
    }

    public String getValue() {
        return value;
    }

    public String getPrice_after() {
        return price_after;
    }

    public String getSub_title() {
        return sub_title;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }
}
