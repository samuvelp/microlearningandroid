package com.samuvel.pandian.microlearning.learninglinks.view;

import java.util.List;

public interface LearningLinksViewContract {
    void populateList(List<String> urls);

    void showError();
}
