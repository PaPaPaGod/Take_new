package com.example.takeretrofit.bean.createorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by price on 1/15/2017.
 */

public class CreateOrderBean {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("msg")
    @Expose
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
