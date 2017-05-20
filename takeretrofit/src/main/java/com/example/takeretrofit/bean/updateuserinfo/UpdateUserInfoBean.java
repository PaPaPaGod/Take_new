package com.example.takeretrofit.bean.updateuserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by price on 1/14/2017.
 */

public class UpdateUserInfoBean {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private UpdateUserInfoData data;

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UpdateUserInfoData getData() {
        return data;
    }

    public void setData(UpdateUserInfoData data) {
        this.data = data;
    }
}
