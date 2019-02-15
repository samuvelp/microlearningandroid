package com.samuvel.pandian.microlearning.subscription.model;

import android.util.Log;

import com.samuvel.pandian.microlearning.subscription.presenter.SubscribePresenterContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class SubscribeModel implements SubscribeModelContract {
    private SubscribePresenterContract mSubscribePresenterContract;

    public void attachPresenter(SubscribePresenterContract subscribePresenterContract) {
        this.mSubscribePresenterContract = subscribePresenterContract;
    }

    public void detachPresenter() {
        this.mSubscribePresenterContract = null;
    }


    @Override
    public void subscribe(String deviceToken, String userId, String topic) {
        Log.d("requestPOST", "" + "dt:" + deviceToken + " userId:" + userId + "topic" + topic);
        postSubscribeRequest(deviceToken, userId, topic);
    }

    private void postSubscribeRequest(String deviceToken, String userId, String topic) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
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

    private void getUserLearningLinks(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MicrolearningService service = retrofit
                .create(MicrolearningService.class);
        Call<List<String>> call = service.getLearningLinks(userId);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

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

        @GET("learninglinks/{uuid}")
        Call<List<String>> getLearningLinks(@Path("uuid") String uuid);
    }
}
