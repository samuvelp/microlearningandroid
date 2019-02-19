package com.samuvel.pandian.microlearning.subscription.model;

import android.content.Context;
import android.util.Log;

import com.samuvel.pandian.microlearning.R;
import com.samuvel.pandian.microlearning.subscription.presenter.SubscribePresenterContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SubscribeModel implements SubscribeModelContract {
    private SubscribePresenterContract mSubscribePresenterContract;
    private Context mContext;

    public void attachPresenter(Context context,SubscribePresenterContract subscribePresenterContract) {
        this.mSubscribePresenterContract = subscribePresenterContract;
        this.mContext = context;
    }

    public void detachPresenter() {
        this.mSubscribePresenterContract = null;
        this.mContext = null;
    }


    @Override
    public void subscribe(String deviceToken, String userId, String topic) {
        Log.d("requestPOST", "" + "dt:" + deviceToken + " userId:" + userId + "topic" + topic);
        postSubscribeRequest(deviceToken, userId, topic);
    }

    private void postSubscribeRequest(String deviceToken, String userId, String topic) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getResources().getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MicrolearningService service = retrofit
                .create(MicrolearningService.class);
        Call<String> call = service.subscribeToTopic(userId, topic, deviceToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (getPresenter() != null) {
                    getPresenter().onSubscribeSuccess();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (getPresenter() != null) {
                    getPresenter().onSubscribeFailed();
                }
            }
        });
    }

    private SubscribePresenterContract getPresenter() {
        return mSubscribePresenterContract;
    }

    private interface MicrolearningService {
        @GET("subscribe/{uuid}/{topic}/{devicetoken}")
        Call<String> subscribeToTopic(@Path("uuid") String uuid,
                                      @Path("topic") String topic,
                                      @Path("devicetoken") String devicetoken);

    }
}
