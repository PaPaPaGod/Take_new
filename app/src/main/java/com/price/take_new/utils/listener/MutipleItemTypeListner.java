package com.price.take_new.utils.listener;

/**
 * Created by price on 2016/11/14.
 */

public interface MutipleItemTypeListner<T> {
    int getLayoutId(int itemType);
    int getItemViewType(int position, T t);
}
