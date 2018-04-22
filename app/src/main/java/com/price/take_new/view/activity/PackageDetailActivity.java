package com.price.take_new.view.activity;

import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.view.fragment.express.PackageDetailFragment;

/**
 * Created by intel on 3/2/2018.
 */

public class PackageDetailActivity extends AppActivity {


    @Override
    protected BaseFragment getFirstFragment() {
        return new PackageDetailFragment();
    }
}
