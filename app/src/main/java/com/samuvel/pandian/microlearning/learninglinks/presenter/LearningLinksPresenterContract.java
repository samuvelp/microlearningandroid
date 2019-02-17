package com.samuvel.pandian.microlearning.learninglinks.presenter;

import java.util.List;

public interface LearningLinksPresenterContract {
    void getLearningLinks();

    void onLearningLinksSuccess(List<String> learningLinks);

    void onLearningLinksFailed();
}
