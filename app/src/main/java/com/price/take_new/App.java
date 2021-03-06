package com.price.take_new;


import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.price.take_new.bean.DaoMaster;
import com.price.take_new.bean.DaoSession;

import io.rong.imkit.RongIM;
import io.rong.push.RongPushClient;
import io.rong.push.common.RongException;

/**
 * Created by price on 1/16/2017.
 */

public class App extends Application {
    private static final String MIAPP_KEY = "5521756523703";
    private static final String MIAPP_ID = "2882303761517565703";


    private static Context context;

    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static App instances;


    /**
     * 初始化greenDao，这个操作建议在Application初始化的时候添加；
     */
    private void initDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "cache-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }

    public static App getInstances(){
        if(instances!=null) {
            return instances;
        }
        return new App();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RongPushClient.registerMiPush(this,MIAPP_ID,MIAPP_KEY);
        RongPushClient.registerHWPush(this);
        RongIM.init(this);
        initDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return context;
    }
}
