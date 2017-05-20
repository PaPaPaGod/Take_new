package com.price.take_new.presenter;

import com.price.take_new.model.FeedBackModel;
import com.price.take_new.service.api.OnFeedBackListener;
import com.price.take_new.service.modelService.IFeedBackModel;
import com.price.take_new.service.viewService.IFeedBackView;

/**
 * Created by price on 2/23/2017.
 */

public class FeedBackPresenter {
    private IFeedBackModel feedBackModel;
    private IFeedBackView feedBackView;

    public FeedBackPresenter(IFeedBackView feedBackView) {
        this.feedBackView = feedBackView;
        this.feedBackModel = new FeedBackModel();
    }

    public void feedBack(String token,String des){
        feedBackModel.feedBack(token, des, new OnFeedBackListener() {
            @Override
            public void onSuccess(String msg, int code) {
                feedBackView.showToast(msg,code);
            }

            @Override
            public void onError(String msg, int code) {
                feedBackView.showToast(msg,code);
            }
        });
    }
}
