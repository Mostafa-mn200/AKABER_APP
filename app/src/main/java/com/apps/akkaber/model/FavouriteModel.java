package com.apps.akkaber.model;

import java.io.Serializable;

public class FavouriteModel implements Serializable {
    public int id;
    public String photo;
    public String title;

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
