
package com.example.takeretrofit.bean.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendDatum {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("ry_token")
    @Expose
    private String ryToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

}
