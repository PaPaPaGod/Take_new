
package com.example.takeretrofit.bean.msg;

import com.example.takeretrofit.bean.login.LoginData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MsgBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private MsgData data;

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

    public MsgData getData() {
        return data;
    }

    public void setData(MsgData data) {
        this.data = data;
    }

}
