package com.price.take_new.view.activity;

import android.app.Activity;
import android.widget.Toast;

import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.view.fragment.login.LoginFragment;

/**
 * Created by price on 1/12/2017.
 */

public class LoginAcitivity extends AppActivity{
    @Override
    protected BaseFragment getFirstFragment() {
        return LoginFragment.newInstance();
    }

}
