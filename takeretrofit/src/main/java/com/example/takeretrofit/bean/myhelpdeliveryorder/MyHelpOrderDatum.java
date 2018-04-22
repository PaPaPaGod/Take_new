
package com.example.takeretrofit.bean.myhelpdeliveryorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyHelpOrderDatum {

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
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("take_time")
    @Expose
    private String takeTime;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("at_school")
    @Expose
    private int at_school;

    @SerializedName("weight_type")
    @Expose
    private String weight_type;

    @SerializedName("small_reward")
    @Expose
    private String small_reward;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("sms_content")
    @Expose
    private String sms_content;

    public int getFrom_weixin() {
        return from_weixin;
    }

    public void setFrom_weixin(int from_weixin) {
        this.from_weixin = from_weixin;
    }

    @SerializedName("from_weixin")
    @Expose
    private int from_weixin;

    public int getAt_school() {
        return at_school;
    }

    public void setAt_school(int at_school) {
        this.at_school = at_school;
    }

    public String getWeight_type() {
        return weight_type;
    }

    public void setWeight_type(String weight_type) {
        this.weight_type = weight_type;
    }

    public String getSmall_reward() {
        return small_reward;
    }

    public void setSmall_reward(String small_reward) {
        this.small_reward = small_reward;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSms_content() {
        return sms_content;
    }

    public void setSms_content(String sms_content) {
        this.sms_content = sms_content;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
