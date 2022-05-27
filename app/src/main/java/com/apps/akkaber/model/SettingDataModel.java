package com.apps.akkaber.model;

import java.io.Serializable;
import java.util.List;

public class SettingDataModel extends StatusResponse implements Serializable {
    private SettingModel data;

    public SettingModel getData() {
        return data;
    }


}

