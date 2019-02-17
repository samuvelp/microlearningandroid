package com.samuvel.pandian.microlearning.learninglinks.presenter;

public interface LearningLinksPresenterContract {
    void getLearningLinks(String userId);

    void onLearningLinksSuccess(Object[] learningLinks);

    void onLearningLinksFailed();
}
