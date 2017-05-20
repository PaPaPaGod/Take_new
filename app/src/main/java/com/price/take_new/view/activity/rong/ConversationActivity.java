package com.price.take_new.view.activity.rong;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.price.take_new.R;

import io.rong.imkit.RongIM;

/**
 * Created by price on 2/24/2017.
 */

public class ConversationActivity extends FragmentActivity {

    private TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        mName = (TextView) findViewById(R.id.name);

        String sId = getIntent().getData().getQueryParameter("targetId");//targetId:单聊即对方ID，群聊即群组ID
        String sName = getIntent().getData().getQueryParameter("title");//获取昵称

        if (!TextUtils.isEmpty(sName)){
            mName.setText(sName);
        }else {
//            sId
        }
    }

}
