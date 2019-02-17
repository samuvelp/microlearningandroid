package com.samuvel.pandian.microlearning.learninglinks.model;


import com.samuvel.pandian.microlearning.learninglinks.presenter.LearningLinksPresenterContract;

public class LearningLinksModel {
    LearningLinksPresenterContract mLearningLinksPresenterContract;

    private void attachPresenter(LearningLinksPresenterContract learningLinksPresenterContract){
        this.mLearningLinksPresenterContract = learningLinksPresenterContract;
    }
    private void detachPresenter(){
        this.mLearningLinksPresenterContract = null;
    }
}
