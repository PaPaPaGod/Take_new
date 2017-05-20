package com.example.takeretrofit.bean.sysnotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by price on 3/15/2017.
 */

public class NotificationDatum {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("other_id")
    @Expose
    private String other_id;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("is_taker")
    @Expose
    private String is_taker;

    @SerializedName("order_id")
    @Expose
    private String order_id;

    @SerializedName("created")
    @Expose
    private String created;

    public String getOther_name() {
        return other_name;
    }

    public void setOther_name(String other_name) {
        this.other_name = other_name;
    }

    @SerializedName("other_name")
    @Expose
    private String other_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOther_id() {
        return other_id;
    }

    public void setOther_id(String other_id) {
        this.other_id = other_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIs_taker() {
        return is_taker;
    }

    public void setIs_taker(String is_taker) {
        this.is_taker = is_taker;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
