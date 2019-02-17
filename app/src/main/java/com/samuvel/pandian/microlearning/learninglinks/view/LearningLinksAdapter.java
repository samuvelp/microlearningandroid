package com.samuvel.pandian.microlearning.learninglinks.view;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samuvel.pandian.microlearning.R;

import java.util.List;

public class LearningLinksAdapter extends RecyclerView.Adapter<LearningLinksViewHolder> {
    private List<String> mUserLinks;

    protected LearningLinksAdapter(List<String> userLinks) {
        this.mUserLinks = userLinks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LearningLinksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_learning_link, viewGroup, false);
        LearningLinksViewHolder holder = new LearningLinksViewHolder(rowView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLinksViewHolder learningLinksViewHolder, int i) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            learningLinksViewHolder.textViewLink.setText(Html.fromHtml(mUserLinks.get(i),Html.FROM_HTML_MODE_COMPACT));
//        }else{
//            learningLinksViewHolder.textViewLink.setText(Html.fromHtml(mUserLinks.get(i)));
//        }
        learningLinksViewHolder.textViewLink.setText(mUserLinks.get(i));

    }

    @Override
    public int getItemCount() {
        return mUserLinks.size();
    }
}
