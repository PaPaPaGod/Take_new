package com.price.take_new.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.view.fragment.login.SetInfoFragment;

/**
 * Created by price on 2/11/2017.
 */
public class SetInfoActivity extends AppActivity{
    @Override
    protected BaseFragment getFirstFragment() {
        return SetInfoFragment.newInstance();
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent!=null){
            String token = intent.getStringExtra(Constant.KEY_TOKEN);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_TOKEN,token);
            getFirstFragment().setArguments(bundle);
        }
    }
}
