package com.apps.akkaber.model;

import java.io.Serializable;
import java.util.List;

public class FavouriteDataModel extends StatusResponse implements Serializable {
    private List<FavouriteModel> data;

    public List<FavouriteModel> getData() {
        return data;
    }

    public void setData(List<FavouriteModel> data) {
        this.data = data;
    }
}
