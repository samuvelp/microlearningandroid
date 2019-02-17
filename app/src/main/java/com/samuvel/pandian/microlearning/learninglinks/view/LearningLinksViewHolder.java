package com.samuvel.pandian.microlearning.learninglinks.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.samuvel.pandian.microlearning.R;

public class LearningLinksViewHolder extends RecyclerView.ViewHolder {
    TextView textViewLink;

    public LearningLinksViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewLink = itemView.findViewById(R.id.textview_link);
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
