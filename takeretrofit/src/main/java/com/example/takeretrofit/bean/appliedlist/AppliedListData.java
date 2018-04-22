
package com.example.takeretrofit.bean.appliedlist;

import com.example.takeretrofit.bean.schoollist.SchoolListDatum;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppliedListData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("phone_num")
    @Expose
    private String phone_num;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("school_id")
    @Expose
    private int school_id;

    @SerializedName("major")
    @Expose
    private String major;

    @SerializedName("auth")
    @Expose
    private int auth;

    @SerializedName("created")
    @Expose
    private long created;

    @SerializedName("last_login")
    @Expose
    private long last_login;

    @SerializedName("ry_token")
    @Expose
    private String ry_token;

    @SerializedName("stu_id")
    @Expose
    private String stu_id;

    @SerializedName("taken_express_count")
    @Expose
    private int taken_express_count;

    @SerializedName("school")
    @Expose
    private SchoolListDatum school;

    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
    public String getPhone_num() {
        return phone_num;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }
    public int getSchool_id() {
        return school_id;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public String getMajor() {
        return major;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
    public int getAuth() {
        return auth;
    }

    public void setCreated(long created) {
        this.created = created;
    }
    public long getCreated() {
        return created;
    }

    public void setLast_login(long last_login) {
        this.last_login = last_login;
    }
    public long getLast_login() {
        return last_login;
    }

    public void setRy_token(String ry_token) {
        this.ry_token = ry_token;
    }
    public String getRy_token() {
        return ry_token;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }
    public String getStu_id() {
        return stu_id;
    }

    public void setTaken_express_count(int taken_express_count) {
        this.taken_express_count = taken_express_count;
    }
    public int getTaken_express_count() {
        return taken_express_count;
    }

    public void setSchool(SchoolListDatum school) {
        this.school = school;
    }
    public SchoolListDatum getSchool() {
        return school;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
    public Pivot getPivot() {
        return pivot;
    }

}
