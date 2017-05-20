package com.price.take_new.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.price.take_new.Constant;
import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/24/2017.
 */

public class ManagerUserInfo {

    public static boolean isNull(Context context){
        String name = "";
        name = getInfo(context).getName();
        if(!name.equals(null)){
            if(name.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void cacheInfo(Context context, UserInfo info) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Constant.KEY_NAME,info.getName());
        editor.putString(Constant.KEY_PHONE_NUM,info.getPhoneNum());
        editor.putString(Constant.KEY_SEX,info.getSex());
        editor.putString(Constant.KEY_SCHOOL,info.getSchool());
        editor.putString(Constant.KEY_MAJOR,info.getMajor());
        editor.putString(Constant.KEY_isCertify,info.getAuth());
        editor.putString(Constant.KEY_STU_ID,info.getStuId());
        editor.commit();
    }

    public static UserInfo getInfo(Context context){
        UserInfo info = new UserInfo();
        SharedPreferences preferences = context.getSharedPreferences(Constant.APP_PACKAGE_NAME,Context.MODE_PRIVATE);

        info.setMajor(preferences.getString(Constant.KEY_MAJOR,null));
        info.setPhoneNum(preferences.getString(Constant.PHONE_NUM,null));
        info.setStuId(preferences.getString(Constant.KEY_STU_ID,null));
        info.setName(preferences.getString(Constant.KEY_NAME,null));
        info.setSex(preferences.getString(Constant.KEY_SEX,null));
        info.setSchool(preferences.getString(Constant.KEY_SCHOOL,null));
        info.setAuth(preferences.getString(Constant.KEY_isCertify,null));

        return info;
    }

    public static void clear(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(Constant.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Constant.KEY_NAME,"");
        editor.putString(Constant.KEY_PHONE_NUM,"");
        editor.putString(Constant.KEY_SEX,"");
        editor.putString(Constant.KEY_SCHOOL,"");
        editor.putString(Constant.KEY_MAJOR,"");
        editor.putString(Constant.KEY_isCertify,"");
        editor.putString(Constant.KEY_STU_ID,"");
        editor.commit();
    }

}
