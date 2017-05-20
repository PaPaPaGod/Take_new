
package com.example.takeretrofit.bean.mydeliveryorder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyDeliveryOrderBean {

    @SerializedName("code")
    @Expose
    private Integer code;

    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private List<MyDeliveryOrderDatum> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MyDeliveryOrderDatum> getData() {
        return data;
    }

    public void setData(List<MyDeliveryOrderDatum> data) {
        this.data = data;
    }

}
