package com.price.take_new.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.takeretrofit.model.GetOrderDetail;
import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.R;


/**
 * Created by price on 1/12/2017.
 */

public class MainActivity extends AppActivity {
    private TextView tv_token;
    private Button btn_login;

    private String token_132;
    private String token_158;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token_132 = "AGYCYVQ4DmJRbwQ2XTUAYAEzVmRVa1Fi";
        token_158 = "AGYCYVQ4DmNRZwQ4XTEAZwE2VmtValFo";
        initView();
    }

    private void initView() {
        tv_token = (TextView) findViewById(R.id.tv_token);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick_test","Onclick");
                new GetOrderDetail().getOrderDetail(token_132,"124");
//                new GetMyDelivery().getMyDelivery(token,"0");
//                new GetOrder().getOrder(token,"62","125");
//                new Login().login("15888888888","123");
//                new Register().register("13222222228","123");
//                new GetSchoolList().getSchoolList("AYCYVQ4DmJRbwQ2XTUAYAEzVmRVa1Fi");
//                new GetDelivery().getDelivery("AYCYVQ4DmJRbwQ2XTUAYAEzVmRVa1Fi","0");
//                UpdateUserInfoBean updateUserInfo = new UpdateUserInfoBean();
//                updateUserInfo.updateUserInfo(token,"tan.1",
//                        "男","1","what");
//                new GetUserInfo().getUserInfo(token);
//                new GetOtherUserInfo().getOtherUserInfo(token,"62");
//                new Authenticate().authenticate(token,"32","123");
//                new SubmitAdvice().submitAdvice(token,"my advice");
//                new CreateDevilery().createDevilery(token,"company","des","address","place","0","take_time");
//                new GetMyHelpOrder().getMyHelpOrder(token_158,"0");
            }
        });
    }
}
