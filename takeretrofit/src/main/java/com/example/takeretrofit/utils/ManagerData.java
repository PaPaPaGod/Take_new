package com.example.takeretrofit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

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

    public static void cacheAuth(Context context,int authoStatus){
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putInt(Config.KEY_IS_AUTH,authoStatus);
        editor.commit();
    }

    public static int getAuth(Context context){
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getInt(Config.KEY_IS_AUTH,Config.AUTH_NOT);
    }

    public static void clearCachedData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public static void cacheTBName(Context context,String tbname) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_TB_NAME,tbname);
        editor.commit();
    }

    public static void cacheTrade(Context context,String trade){
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PACKAGE_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Config.KEY_TRADE,trade);
        editor.commit();
    }

    public static String getCachedTrade(Context context) {
        return context.getSharedPreferences(Config.APP_PACKAGE_NAME,Context.MODE_PRIVATE).
                getString(Config.KEY_TRADE,null);
    }
}
