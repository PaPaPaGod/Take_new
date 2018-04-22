package com.price.take_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by price on 2/21/2017.
 */

public abstract class HomeBaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView toolBarTitle;
    private String title;

    public abstract void initView();

    public abstract String getToolBarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        initView();
        setToolbar(getToolBarTitle());
    }

    public int getContentViewId(){
        return R.layout.activity_base;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            moveTaskToBack(false);
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    public void setToolbar(String title){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        toolBarTitle = (TextView) findViewById(R.id.toolbar);
        toolBarTitle.setText(title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
