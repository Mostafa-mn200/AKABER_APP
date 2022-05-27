package com.apps.akkaber.model;

import java.io.Serializable;
import java.util.List;

public class SingleDepartmentDataModel extends StatusResponse implements Serializable {
    private DepartmentModel data;

    public DepartmentModel getData() {
        return data;
    }

    public void setData(DepartmentModel data) {
        this.data = data;
    }
}

