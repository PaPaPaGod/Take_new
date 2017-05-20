package com.example.takeretrofit.bean.sysnotification;

import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public class NotificationBean {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<NotificationDatum> data = null;

    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<NotificationDatum> getData() {
        return data;
    }

    public void setData(List<NotificationDatum> data) {
        this.data = data;
    }
}
