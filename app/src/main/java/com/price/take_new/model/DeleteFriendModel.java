package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.DeleteFriendsApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.DeleteFriendListener;
import com.price.take_new.service.modelService.IDeleteFriendModel;

/**
 * Created by intel on 3/29/2018.
 */

public class DeleteFriendModel implements IDeleteFriendModel {
    private String tag = "DeleteFriend_test";

    @Override
    public void deleteFriend(String token, String user_id, final DeleteFriendListener listener) {
        ServiceFactory.getInstance().createService(DeleteFriendsApi.class)
                .deleteFriend(token,user_id)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        listener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        listener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
