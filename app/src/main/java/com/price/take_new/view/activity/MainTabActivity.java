package com.price.take_new.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.view.fragment.express.PackageDetailFragment;
import com.price.take_new.view.fragment.express.PublishFragment;
import com.price.take_new.view.fragment.home.NotificationFragment;
import com.price.take_new.view.fragment.personal.AuthorizationFragment;
import com.price.take_new.view.fragment.personal.FeedBackFragment;
import com.price.take_new.view.fragment.personal.ManageTradeFragment;
import com.price.take_new.view.fragment.personal.SettingFragment;

/**
 * Created by intel on 3/16/2018.
 */

public class MainTabActivity extends AppActivity {
    private String TAG = "MainTabActivity";
    private Bundle bundle;

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        fragment_index = intent.getIntExtra(Constant.FRAGMENT_NAME,0);
        bundle = intent.getExtras();
    }

    @Override
    protected BaseFragment getFirstFragment() {
        switch (fragment_index){
            case Constant.MAIN_PACKAGE_DETAIL:
                PackageDetailFragment fra = new PackageDetailFragment();
                if(bundle!=null){
                    fra.setArguments(bundle);
                }
                return fra;
            case Constant.MAIN_PACKAGE_PUBLISH:
                return new PublishFragment();
            case Constant.EDIT_TRADE:
                return new ManageTradeFragment();
            case Constant.SETTING:
                return new SettingFragment();
            case Constant.MAIN_NOTIFICATION:
                return new NotificationFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.e(TAG,"maintab result ok");
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
        }
    }
}
