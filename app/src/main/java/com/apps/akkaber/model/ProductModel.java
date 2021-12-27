package com.apps.akkaber.model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String id;
    private String photo;
    private String title;
    private String category_id;

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
}
