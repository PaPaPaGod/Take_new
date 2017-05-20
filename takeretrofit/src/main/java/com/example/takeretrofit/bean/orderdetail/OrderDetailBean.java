
package com.example.takeretrofit.bean.orderdetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private OrderDetailData data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public OrderDetailData getData() {
        return data;
    }

    public void setData(OrderDetailData data) {
        this.data = data;
    }

}
