package com.example.takeretrofit;

/**
 * Created by price on 1/12/2017.
 */

public class Config {
    //状态码
    public static final int SUCCESS_WITH_STATUS = 1;    //请求成功，只返回成功信息
    public static final int SUCCESS_WITH_DATAS = 0;     //请求成功，返回具体数据和成功信息
    public static final int FAILED_WITH_STATUS = 100;   //请求失败，只返回请求失败提示信息
    public static final int TOKEN_INVALED = 101;        //token过期

    //url接入
    public static final String REGISTER_OR_LOGIN_URL = "http://takeapp.com.cn/Home/register/";
    public static final String EXPRESS_URL = "http://takeapp.com.cn/Home/express/";
    public static final String USER_URL = "http://takeapp.com.cn/Home/user/";
    public static final String SYSTEM_URL = "http://takeapp.com.cn/Home/system/";

    public static final String APP_PACKAGE_NAME = "com.price.take_new";

    //关键字
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_RONG_TOKEN = "ry_token";
    public static final String KEY_PHONE_NUM = "phone_num";
    public static final String KEY_NAME = "name";
    public static final String KEY_SEX = "sex";
    public static final String KEY_SCHOOL_ID = "school_id";
    public static final String KEY_MAJOR = "major";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_DES = "des";
    public static final String KEY_STU_ID = "stu_id";
    public static final String KEY_STU_PASSWORD = "stu_password";
    public static final String KEY_COMPANY = "company";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PLACE = "place";
    public static final String KEY_PRICE = "price";
    public static final String KEY_TAKE_TIME = "take_time";
    public static final String KEY_POSTER_ID = "poster_id";
    public static final String KEY_EXPRESS_ID = "express_id";
    public static final String KEY_PAGE = "page";
    public static final String KEY_ORDER_ID = "order_id";
    public static final String KEY_SIZE = "size";
    public static final String KEY_IS_AUTH = "isAuth";
    public static final String KEY_SMS_CONTENT = "sms_content";
}
