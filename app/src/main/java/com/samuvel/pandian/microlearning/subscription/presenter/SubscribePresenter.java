package com.samuvel.pandian.microlearning.subscription.presenter;

import android.content.Context;

import com.samuvel.pandian.microlearning.subscription.model.SubscribeModel;
import com.samuvel.pandian.microlearning.subscription.model.SubscribeModelContract;
import com.samuvel.pandian.microlearning.subscription.view.SubscribeViewContract;

public class SubscribePresenter implements SubscribePresenterContract {
    private SubscribeViewContract mSubscribeViewContract;
    private SubscribeModelContract mSubscribeModelContract;
    private Context mContext;

    public void attachView(Context context, SubscribeViewContract subscribeViewContract) {
        this.mContext = context;
        this.mSubscribeViewContract = subscribeViewContract;
        this.mSubscribeModelContract = new SubscribeModel();
        ((SubscribeModel) mSubscribeModelContract).attachPresenter(this);
    }

    public void detachView() {
        this.mContext = null;
        this.mSubscribeViewContract = null;
        ((SubscribeModel) mSubscribeModelContract).detachPresenter();
        mSubscribeModelContract = null;
    }

    @Override
    public void subscribe(String deviceToken, String userId, String topic) {
        if (getModel() != null)
            getModel().subscribe(deviceToken, userId, topic);
    }

    @Override
    public void onSubscribeSuccess() {
        if(getView()!=null)
            getView().onSubscribed();
    }

    @Override
    public void onSubscribeFailed() {
        if(getView()!=null)
            getView().onSubscribed();
    }

    private SubscribeViewContract getView() {
        return mSubscribeViewContract;
    }

    private SubscribeModelContract getModel() {
        return mSubscribeModelContract;
    }
}
