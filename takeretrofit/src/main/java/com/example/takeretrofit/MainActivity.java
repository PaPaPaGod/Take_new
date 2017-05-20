package com.example.takeretrofit;

import android.app.Activity;
import android.os.Bundle;

import com.example.takeretrofit.model.GetSysNotification;

/**
 * Created by price on 3/15/2017.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetSysNotification().getSysNotification("AGYCYVQ5DmZRZgQ3XTcAZQE5VmpVZVFh","0","88");
    }
}
