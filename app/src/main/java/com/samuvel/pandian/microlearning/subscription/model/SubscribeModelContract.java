package com.samuvel.pandian.microlearning.subscription.model;

public interface SubscribeModelContract {
    void subscribe(String deviceToken, String userId, String topic);

}
