package com.price.take_new.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.view.fragment.login.SetUserMsgFragment;
import com.price.take_new.view.fragment.pager.MyHelpPackagePager;
import com.price.take_new.view.fragment.personal.PackageHelpDetailFragment;
import com.price.take_new.view.fragment.personal.PackageMyDetailFragment;
import com.price.take_new.view.fragment.personal.SettingFragment;
import com.price.take_new.view.fragment.personal.AuthorizationFragment;
import com.price.take_new.view.fragment.personal.FeedBackFragment;
import com.price.take_new.view.fragment.personal.ManageTradeFragment;

/**
 * Created by intel on 2/15/2018.
 */

public class PersonalMsgActivity extends AppActivity {
    private static final String TAG = "PersonalMsgActivity";
    private Bundle bundle;

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        fragment_index = intent.getIntExtra(Constant.FRAGMENT_NAME,0);
        bundle = intent.getExtras();
    }

    @Override
    protected BaseFragment getFirstFragment() {
//        removeFragment();
        switch (fragment_index){
            case Constant.FEEDBACK:
                return FeedBackFragment.newInstance();
            case Constant.AUTHORIZATION:
                return AuthorizationFragment.newInstance();
            case Constant.EDIT_TRADE:
                return new ManageTradeFragment();
            case Constant.SETTING:
                return new SettingFragment();
            case Constant.EDIT_INFO:
                return SetUserMsgFragment.newInstance();
            case Constant.MY_PACKAGE_DETAIL:
                PackageMyDetailFragment fra = PackageMyDetailFragment.newInstance();
                if(bundle!=null){
                    Log.e(TAG,bundle.getString(Constant.KEY_ORDER_ID));
                    fra.setArguments(bundle);
                }
                return fra;
            case Constant.HELP_PACKAGE_DETAIL:
                PackageHelpDetailFragment fra1 = PackageHelpDetailFragment.newInstance();
                if(bundle!=null){
                    fra1.setArguments(bundle);
                }
                return fra1;
            default:
                break;
        }
        return null;
    }
}
