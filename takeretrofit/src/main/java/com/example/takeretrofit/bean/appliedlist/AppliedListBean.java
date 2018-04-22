package com.example.takeretrofit.bean.appliedlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by intel on 4/22/2018.
 */

public class AppliedListBean {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private AppliedListData data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public AppliedListData getData() {
        return data;
    }

    public void setData(AppliedListData data) {
        this.data = data;
    }
}
