package com.example.takeretrofit.bean.updateuserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by price on 1/14/2017.
 */

public class UpdateUserInfoData {
    @SerializedName("data")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
