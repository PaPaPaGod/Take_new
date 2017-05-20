package com.example.takeretrofit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.takeretrofit.Config;


/**
 * Created by Administrator on 2016/8/19.
 */
public class ManagerData {

    public static String getCachedPassword(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getString(Config.KEY_PASSWORD,null);
    }

    public static String getCachedToken(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getString(Config.KEY_TOKEN,null);
    }

    public static String getCachedRongToken(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getString(Config.KEY_RONG_TOKEN,null);
    }

    public static void cachePassword(Context context , String rongToken){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_PASSWORD,rongToken);
        editor.commit();
    }

    public static void cacheToken(Context context , String token){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_TOKEN,token);
        editor.commit();
    }

    public static void cacheRongToken(Context context , String rongToken){
        SharedPreferences.Editor editor = context.getSharedPreferences
                (Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_RONG_TOKEN,rongToken);
        editor.commit();
    }

    public static void cachePhoneNum(Context context, String phoneNum) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_PHONE_NUM,phoneNum);
        editor.commit();
    }

    public static String getPhoneNum(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getString(Config.KEY_PHONE_NUM,null);
    }

    public static void cacheAuth(Context context,boolean isAuth){
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(Config.KEY_IS_AUTH,isAuth);
        editor.commit();
    }

    public static boolean getAuth(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getBoolean(Config.KEY_IS_AUTH,false);
    }

}
