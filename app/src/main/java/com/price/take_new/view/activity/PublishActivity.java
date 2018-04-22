package com.price.take_new.view.activity;


import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.view.fragment.express.PublishFragment;

/**
 * Created by intel on 3/1/2018.
 */

public class PublishActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return new PublishFragment();
    }
}
