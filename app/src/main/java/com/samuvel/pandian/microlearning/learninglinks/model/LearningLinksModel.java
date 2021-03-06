package com.samuvel.pandian.microlearning.learninglinks.model;


import android.content.Context;

import com.samuvel.pandian.microlearning.R;
import com.samuvel.pandian.microlearning.learninglinks.presenter.LearningLinksPresenterContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LearningLinksModel implements LearningLinksModelContract {
    LearningLinksPresenterContract mLearningLinksPresenterContract;
    Context mContext;

    public void attachPresenter(Context context, LearningLinksPresenterContract learningLinksPresenterContract) {
        this.mLearningLinksPresenterContract = learningLinksPresenterContract;
        this.mContext = context;
    }

    public void detachPresenter() {
        this.mLearningLinksPresenterContract = null;
        this.mContext = null;
    }

    @Override
    public void requestLearningLinks(String userId) {
        getUserLearningLinks(userId);
    }

    private void getUserLearningLinks(String userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getString(R.string.ip_address))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LearningLinksModel.MicrolearningService service = retrofit
                .create(LearningLinksModel.MicrolearningService.class);
        Call<List<String>> call = service.getLearningLinks(userId);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (getPresenter() != null) {
                    getPresenter().onLearningLinksSuccess(response.body().toArray());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                if (getPresenter() != null) {
                    getPresenter().onLearningLinksFailed();
                }
            }
        });
    }

    private LearningLinksPresenterContract getPresenter() {
        return mLearningLinksPresenterContract;
    }

    private interface MicrolearningService {
        @GET("userLearningLink/{uuid}")
        Call<List<String>> getLearningLinks(@Path("uuid") String uuid);
    }
}
