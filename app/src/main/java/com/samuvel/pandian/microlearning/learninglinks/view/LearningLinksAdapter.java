package com.samuvel.pandian.microlearning.learninglinks.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class LearningLinksAdapter extends RecyclerView.Adapter<LearningLinksViewHolder> {
    private List<String> mUserLinks;

    protected LearningLinksAdapter(List<String> userLinks) {
        this.mUserLinks = userLinks;
    }

    @NonNull
    @Override
    public LearningLinksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLinksViewHolder learningLinksViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mUserLinks.size();
    }
}
