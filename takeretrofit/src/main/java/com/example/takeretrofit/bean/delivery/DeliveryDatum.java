
package com.example.takeretrofit.bean.delivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//快递列表
public class DeliveryDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("take_time")
    @Expose
    private String takeTime;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sex")
    @Expose
    private String sex;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("is_friend")
    @Expose
    private boolean is_friend;

    @SerializedName("from_weixin")
    @Expose
    private int from_weixin;

    public boolean getIs_friend() {
        return is_friend;
    }

    public void setIs_friend(boolean is_friend) {
        this.is_friend = is_friend;
    }

    public int getFrom_weixin() {
        return from_weixin;
    }

    public void setFrom_weixin(int from_weixin) {
        this.from_weixin = from_weixin;
    }

    public String getSmall_reward() {
        return small_reward;
    }

    public void setSmall_reward(String small_reward) {
        this.small_reward = small_reward;
    }

    @SerializedName("small_reward")
    @Expose
    private String small_reward;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
