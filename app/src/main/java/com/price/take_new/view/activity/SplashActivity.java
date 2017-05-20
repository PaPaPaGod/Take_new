package com.price.take_new.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.takeretrofit.Config;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.R;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.rong.Rong;
import com.price.take_new.view.activity.home.HomeActivity;


/**
 * Created by price on 2/11/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String LOG_TOKEN = "splash_token";
    private static final String LOG_RY_TOKEN = "splash_ry_token";

    private static final long SPLASH_DUR_TIME = 2000;
    private boolean enter = false;
    private String token;
    private String ry_token;

    private GetUserInfoPresenter presenter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case WHAT_INTENT2LOGIN:
                    Toast.makeText(SplashActivity.this,"wtf",Toast.LENGTH_LONG);
                    startActivity(new Intent(SplashActivity.this,LoginAcitivity.class));
                    finish();
                    break;
                case WHAT_INTENT2HOME:
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    Log.e(LOG_TOKEN,token);
                    Log.e(LOG_RY_TOKEN,ry_token);
                    if(token !=null){
                        intent.putExtra(Config.KEY_TOKEN, token);
                    }
                    if (ry_token!= null){
                        intent.putExtra(Config.KEY_RONG_TOKEN,ry_token);
                        Rong.connect(ry_token);
                    }
                    intent.putExtra(Config.KEY_PAGE,"0");
                    startActivity(intent);
                    finish();
                    break;
                case WHAT_INTENT2SET:
                    Intent set = new Intent(SplashActivity.this,SetInfoActivity.class);
                    if(token!=null) {
                        set.putExtra(Config.KEY_TOKEN, token);
                    }
                    Log.e(LOG_TOKEN,token);
                    startActivity(set);
                    finish();
                    break;
            }
            SplashActivity.this.finish();
        }
    };
    private static final int WHAT_INTENT2HOME = 1;
    private static final int WHAT_INTENT2LOGIN = -1;
    private static final int WHAT_INTENT2SET = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        token = ManagerData.getCachedToken(this);
        ry_token = ManagerData.getCachedRongToken(SplashActivity.this);
        if(token == null) {
            Log.e("splash","cachedToken is null");
            handler.sendEmptyMessageDelayed(WHAT_INTENT2LOGIN, SPLASH_DUR_TIME);
        } else {
            if(ry_token == null){
                Log.e("splash","set is process");
                handler.sendEmptyMessageDelayed(WHAT_INTENT2SET,SPLASH_DUR_TIME);
            }else{
                Log.e("splash","home is process");
                handler.sendEmptyMessageDelayed(WHAT_INTENT2HOME, SPLASH_DUR_TIME);
            }
        }
    }

}
