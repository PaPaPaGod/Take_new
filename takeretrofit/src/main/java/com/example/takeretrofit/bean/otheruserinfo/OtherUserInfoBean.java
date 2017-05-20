
package com.example.takeretrofit.bean.otheruserinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherUserInfoBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private OtherUserInfoData data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public OtherUserInfoData getData() {
        return data;
    }

    public void setData(OtherUserInfoData data) {
        this.data = data;
    }

}
