
package com.example.takeretrofit.bean.myhelpdeliveryorder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyHelpOrderBean {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<MyHelpOrderDatum> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<MyHelpOrderDatum> getData() {
        return data;
    }

    public void setData(List<MyHelpOrderDatum> data) {
        this.data = data;
    }

}
