package com.apps.akkaber.model;

import java.io.Serializable;

public class FavouriteModel implements Serializable {
    private int id;
    private String photo;
    private String title;

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }
}
