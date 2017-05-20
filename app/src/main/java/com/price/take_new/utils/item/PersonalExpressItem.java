package com.price.take_new.utils.item;

/**
 * Created by Administrator on 2016/8/7.
 */
public class PersonalExpressItem {
    private String id;  //订单id
    private String user_id; //发布者id
    private String company; //快递公司
    private String des; //描述
    private String address; //收货地点
    private String place;   //交易地点
    private String take_time; //收货时间
    private String price;   //报酬价格（0为没报酬）
    private String created; //创建时间
    private String status;  //快递状态
    private String name;    //姓名

    public PersonalExpressItem(String id, String user_id, String company, String des, String address, String place, String take_time, String price, String created, String status, String name, String accepter_id, String accepter_name) {
        this.id = id;
        this.user_id = user_id;
        this.company = company;
        this.des = des;
        this.address = address;
        this.place = place;
        this.take_time = take_time;
        this.price = price;
        this.created = created;
        this.status = status;
        this.name = name;
        this.accepter_id = accepter_id;
        this.accepter_name = accepter_name;
    }

    //当status为2时即有人接受订单时
    private String accepter_id; //接受订单者id
    private String accepter_name;   //接受订单者name

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getTake_time() {
        return take_time;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccepter_id() {
        return accepter_id;
    }

    public void setAccepter_id(String accepter_id) {
        this.accepter_id = accepter_id;
    }

    public String getAccepter_name() {
        return accepter_name;
    }

    public void setAccepter_name(String accepter_name) {
        this.accepter_name = accepter_name;
    }
}
