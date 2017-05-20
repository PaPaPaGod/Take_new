package com.price.take_new.service.viewService;

import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.price.take_new.utils.item.AllExpressOrderItem;

import java.util.List;

/**
 * Created by price on 2/20/2017.
 */

public interface IGetExpressView {
    void showToast(String msg);
    void showLoading(int index);
    void hideLoading(int index);
    void showData(List<DeliveryDatum> delivery);
}
