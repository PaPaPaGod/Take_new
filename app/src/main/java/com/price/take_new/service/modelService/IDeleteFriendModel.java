package com.price.take_new.service.modelService;

import com.price.take_new.service.api.DeleteFriendListener;

/**
 * Created by intel on 3/29/2018.
 */

public interface IDeleteFriendModel {
    void deleteFriend(String token, String user_id, DeleteFriendListener listener);
}
