
package com.example.takeretrofit.bean.friend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private FriendDatum data;

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

    public FriendDatum getData() {
        return data;
    }

    public void setData(FriendDatum data) {
        this.data = data;
    }

}
