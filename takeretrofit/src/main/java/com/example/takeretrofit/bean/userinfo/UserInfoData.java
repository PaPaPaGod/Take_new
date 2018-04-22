
package com.example.takeretrofit.bean.userinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoData {

    @SerializedName("phone_num")
    @Expose
    private String phoneNum;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("major")
    @Expose
    private String major;
    @SerializedName("auth")
    @Expose
    private int auth;
    @SerializedName("stu_id")
    @Expose
    private String stuId;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

}
