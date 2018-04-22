
package com.example.takeretrofit.bean.msg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsgData {

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
