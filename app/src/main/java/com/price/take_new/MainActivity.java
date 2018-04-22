package com.price.take_new;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.takeretrofit.model.AppliedList;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.bean.UserInfoDao;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private UserInfoDao userInfoDao = App.getDaoSession().getUserInfoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AppliedList().getAppliedList("AGYCYFQyDmJRbgQ1XTUAYgE0VmBVbFFiBG5ZNg==","0","10");
    }
//        Log.e(TAG, MD5Tool.md5("123"));
//        Button btn_save = (Button) findViewById(R.id.btn_save);
//        Button btn_load = (Button) findViewById(R.id.btn_load);
//
//        SearchView searchView = (SearchView) findViewById(R.id.searchview);
//        searchView.setOnQueryTextListener(this);
//
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UserInfo info = new UserInfo();
//                info.setName("tantan");
//                info.setId(111l);
//                userInfoDao.insertOrReplace(info);
//                findUserById("111");
//            }
//        });
//        btn_load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                findUserById("111");
//            }
//        });
//    }
//    private void findUserById(String id){
//        UserInfo myInfo = null;
//        myInfo = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Id.like(id)).unique();
//        if (myInfo != null) {
//            Log.e(TAG,"name:"+myInfo.getName());
//        }else{
//            Log.e(TAG,"user is not exits!");
//        }
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        if (searchView != null) {
//            // 得到输入管理对象
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null) {
//                // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
//                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
//            }
//            mSearchView.clearFocus(); // 不获取焦点
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        return false;
//    }
}
