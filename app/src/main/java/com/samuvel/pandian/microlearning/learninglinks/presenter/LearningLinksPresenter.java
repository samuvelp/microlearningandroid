package com.samuvel.pandian.microlearning.learninglinks.presenter;

import android.content.Context;

import com.samuvel.pandian.microlearning.learninglinks.model.LearningLinksModelContract;
import com.samuvel.pandian.microlearning.learninglinks.view.LearningLinksViewContract;

import java.util.List;

public class LearningLinksPresenter implements LearningLinksPresenterContract{
    private LearningLinksViewContract mLearningLinksViewContract;
    private LearningLinksModelContract mLearningLinksModelContract;
    private Context mContext;

    public void attachView(LearningLinksViewContract learningLinksViewContract,Context context){
        this.mLearningLinksViewContract = learningLinksViewContract;
        this.mContext = context;

    }
    public void detachView(){
        this.mLearningLinksViewContract = null;
    }

    @Override
    public void getLearningLinks() {

    }

    @Override
    public void onLearningLinksSuccess(List<String> learningLinks) {

    }

    @Override
    public void onLearningLinksFailed() {

    }
}
