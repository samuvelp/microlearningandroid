package com.samuvel.pandian.microlearning.learninglinks.presenter;

import android.content.Context;

import com.samuvel.pandian.microlearning.learninglinks.model.LearningLinksModel;
import com.samuvel.pandian.microlearning.learninglinks.model.LearningLinksModelContract;
import com.samuvel.pandian.microlearning.learninglinks.view.LearningLinksViewContract;

import java.util.ArrayList;
import java.util.List;

public class LearningLinksPresenter implements LearningLinksPresenterContract {
    private LearningLinksViewContract mLearningLinksViewContract;
    private LearningLinksModelContract mLearningLinksModelContract;
    private Context mContext;

    public void attachView(LearningLinksViewContract learningLinksViewContract, Context context) {
        this.mLearningLinksViewContract = learningLinksViewContract;
        this.mLearningLinksModelContract = new LearningLinksModel();
        this.mContext = context;
        ((LearningLinksModel) mLearningLinksModelContract).attachPresenter(mContext,this);
    }

    public void detachView() {
        this.mLearningLinksViewContract = null;
        this.mContext = null;
        ((LearningLinksModel) mLearningLinksModelContract).detachPresenter();
        this.mLearningLinksModelContract = null;
    }

    @Override
    public void getLearningLinks(String userId) {
        if (getModel() != null)
            getModel().requestLearningLinks(userId);
    }

    @Override
    public void onLearningLinksSuccess(Object[] learningLinks) {
        if (getView() != null) {
            List<String> urls = new ArrayList<String>();
            for (int i = 0; i < learningLinks.length; i++) {
                urls.add(i, (String) learningLinks[i]);
            }
            getView().populateList(urls);
        }
    }

    @Override
    public void onLearningLinksFailed() {

    }

    private LearningLinksViewContract getView() {
        return mLearningLinksViewContract;
    }

    private LearningLinksModelContract getModel() {
        return mLearningLinksModelContract;
    }
}
