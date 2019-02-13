package com.samuvel.pandian.microlearning.subscription.presenter;

public interface SubscribePresenterContract {
    void subscribe(String deviceToken, String userId, String topic);

    void onSubscribeSuccess();

    void onSubscribeFailed();
}
